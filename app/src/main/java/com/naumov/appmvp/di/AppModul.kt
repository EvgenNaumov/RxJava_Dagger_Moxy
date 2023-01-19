package com.naumov.appmvp.di

import com.naumov.appmvp.App
import com.naumov.appmvp.network.NetworkProvider.createRetrofit
import com.naumov.appmvp.network.NetworkProvider.userApi
import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubInterface
import com.naumov.appmvp.repository.impl.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModul(val app:App) {

    @Provides
    fun app():App {
        return app
    }

}