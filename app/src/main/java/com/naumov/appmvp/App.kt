package com.naumov.appmvp

import android.app.Application
import com.naumov.appmvp.di.AppComponent
import com.naumov.appmvp.di.AppModul
import com.naumov.appmvp.di.DaggerAppComponent
import com.naumov.appmvp.repository.impl.ConnectivityListener
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    lateinit var appComponent: AppComponent

    private lateinit var connectivityListener: ConnectivityListener

    override fun onCreate() {
        super.onCreate()
        instance = this

        connectivityListener = ConnectivityListener(this)


        RxJavaPlugins.setErrorHandler {}
        appComponent = DaggerAppComponent.builder()
            .appModul(AppModul(this))
            .build()
    }

}