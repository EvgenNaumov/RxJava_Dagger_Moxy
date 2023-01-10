package com.naumov.appmvp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.database.GithubAppDb
import com.naumov.appmvp.network.NetworkProvider
import com.naumov.appmvp.repository.impl.GithubRepositoryRepoImpl
import com.naumov.appmvp.repository.impl.GithubRepositoryUserImpl
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class App : Application() {
    companion object {
        lateinit var instance: App
            private set
    }

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigatorHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router

    val appDatabase by lazy { GithubAppDb }
    var repoUser: GithubRepositoryUserImpl = GithubRepositoryUserImpl(NetworkProvider.userApi, appDatabase.getInstance().userDAO, instance.getConnectSingle())
    var repoRepos: GithubRepositoryRepoImpl = GithubRepositoryRepoImpl(NetworkProvider.userApi,appDatabase.getInstance().repoDAO, instance.getConnectSingle())

    private lateinit var connectivityListener: ConnectivityListener
    override fun onCreate() {
        super.onCreate()
        instance = this

        GithubAppDb.create(this)

        connectivityListener =
            ConnectivityListener(applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

        RxJavaPlugins.setErrorHandler {}
    }

    fun getConnectObservable() = connectivityListener.status()
    fun getConnectSingle() = connectivityListener.statusSingle()
}