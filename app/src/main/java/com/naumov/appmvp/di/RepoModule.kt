package com.naumov.appmvp.di

import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubInterface
import com.naumov.appmvp.repository.impl.GithubRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun userRepo(userApi:UsersApi):GithubInterface = GithubRepositoryImpl(userApi)

}