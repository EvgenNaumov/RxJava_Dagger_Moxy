package com.naumov.appmvp.di

import com.naumov.appmvp.database.UserDAO
import com.naumov.appmvp.database.UserDBEntity
import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubUserInterface
import com.naumov.appmvp.repository.INetworkStatus
import com.naumov.appmvp.repository.IRoomGithubUsersCache
import com.naumov.appmvp.repository.impl.GithubRepositoryUserImpl
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    fun userRepo(
        userApi: UsersApi,
        userDao: UserDAO,
        networkStatus: INetworkStatus,
        cacheUserDb: IRoomGithubUsersCache
    ): GithubUserInterface = GithubRepositoryUserImpl(
        userApi,
        userDao,
        networkStatus,
        cacheUserDb
    )
}