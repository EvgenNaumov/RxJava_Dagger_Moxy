package com.naumov.appmvp

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.database.GithubAppDb
import com.naumov.appmvp.network.NetworkProvider
import com.naumov.appmvp.repository.impl.ConnectivityListener
import com.naumov.appmvp.repository.impl.GithubRepositoryRepoImpl
import com.naumov.appmvp.repository.impl.GithubRepositoryUserImpl
import com.naumov.appmvp.repository.impl.RoomGithubRepositoriesCache
import com.naumov.appmvp.repository.impl.RoomGithubUsersCache
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigatorHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router

    private lateinit var appDatabase:GithubAppDb


    lateinit var   repoUser: GithubRepositoryUserImpl
    lateinit var repoRepos: GithubRepositoryRepoImpl
    lateinit var  connectivityListener: ConnectivityListener
    private  var cacheUserDB: RoomGithubUsersCache = RoomGithubUsersCache()
    private var cacheUserRepoDB:RoomGithubRepositoriesCache = RoomGithubRepositoriesCache()

    override fun onCreate() {
        super.onCreate()
        instance = this

        appDatabase = getGithubAppDb(this)
        connectivityListener = ConnectivityListener(this)
        repoUser = GithubRepositoryUserImpl(NetworkProvider.userApi, appDatabase.userDAO, connectivityListener,cacheUserDB)
        repoRepos = GithubRepositoryRepoImpl(NetworkProvider.userApi,appDatabase.repoDAO, connectivityListener, cacheUserRepoDB)


        RxJavaPlugins.setErrorHandler {}
    }

    private fun getGithubAppDb(cont:Context):GithubAppDb  {
        GithubAppDb.create(cont)
        return GithubAppDb.getInstance()
    }


}