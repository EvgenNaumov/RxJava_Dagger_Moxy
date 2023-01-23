package com.naumov.appmvp.di

import com.naumov.appmvp.database.ReposDAO
import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubRepoInterface
import com.naumov.appmvp.repository.INetworkStatus
import com.naumov.appmvp.repository.IRoomGithubRepositoriesCache
import com.naumov.appmvp.repository.IRoomGithubUsersCache
import com.naumov.appmvp.repository.impl.GithubRepositoryRepoImpl
import dagger.Module
import dagger.Provides

@Module
class RepoRepoModul {

    @Provides
    fun repoRepo(
        userApi: UsersApi,
        repoDAO: ReposDAO,
        networkStatus: INetworkStatus,
        cacheRepoDb: IRoomGithubRepositoriesCache
    ):GithubRepoInterface = GithubRepositoryRepoImpl(
        userApi,
        repoDAO, networkStatus, cacheRepoDb
    )
}
