package com.naumov.appmvp

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.naumov.appmvp.network.NetworkProvider
import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubInterface
import com.naumov.appmvp.repository.impl.GithubRepositoryImpl

class App : Application(){
    companion object{
        lateinit var instance: App
        private set
    }
    private val cicerone:Cicerone<Router> by lazy { Cicerone.create() }

    val navigatorHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router
    val repo = GithubRepositoryImpl(NetworkProvider.userApi)
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}