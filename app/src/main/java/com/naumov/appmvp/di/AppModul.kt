package com.naumov.appmvp.di

import com.naumov.appmvp.App
import dagger.Module
import dagger.Provides

@Module
class AppModul(val app:App) {

    @Provides
    fun app():App {
        return app
    }

}