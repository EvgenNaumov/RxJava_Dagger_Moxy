package com.naumov.appmvp

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.database.GithubAppDb
import com.naumov.appmvp.di.AppComponent
import com.naumov.appmvp.di.AppModul
import com.naumov.appmvp.di.DaggerAppComponent
import com.naumov.appmvp.network.NetworkProvider
import com.naumov.appmvp.repository.impl.*
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: AppComponent

    private lateinit var appDatabase: GithubAppDb

    lateinit var repoUser: GithubRepositoryUserImpl
    lateinit var repoRepos: GithubRepositoryRepoImpl
    lateinit var connectivityListener: ConnectivityListener
    private var cacheUserDB: RoomGithubUsersCache = RoomGithubUsersCache()
    private var cacheUserRepoDB: RoomGithubRepositoriesCache = RoomGithubRepositoriesCache()

    override fun onCreate() {
        super.onCreate()
        instance = this

        connectivityListener = ConnectivityListener(this)
        repoUser = GithubRepositoryUserImpl(
            NetworkProvider.userApi,
            appDatabase.userDAO,
            connectivityListener,
            cacheUserDB
        )
        repoRepos = GithubRepositoryRepoImpl(
            NetworkProvider.userApi,
            appDatabase.repoDAO,
            connectivityListener,
            cacheUserRepoDB
        )


        RxJavaPlugins.setErrorHandler {}
        appComponent = DaggerAppComponent.builder()
            .appModul(AppModul(this))
            .build()
    }

}