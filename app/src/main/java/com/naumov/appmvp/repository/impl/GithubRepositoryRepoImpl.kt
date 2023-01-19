package com.naumov.appmvp.repository.impl

import com.naumov.appmvp.database.ReposDAO
import com.naumov.appmvp.doCompletableIf
import com.naumov.appmvp.mapper.ForkMapper
import com.naumov.appmvp.mapper.RepoMapper
import com.naumov.appmvp.model.ForkRepoEntity
import com.naumov.appmvp.model.UserRepoEntity
import com.naumov.appmvp.network.NetworkProvider.userApi
import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubRepoInterface
import com.naumov.appmvp.repository.INetworkStatus
import com.naumov.appmvp.repository.IRoomGithubRepositoriesCache
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class GithubRepositoryRepoImpl(
    private val userApi: UsersApi,
    private val repoDao: ReposDAO,
    private val networkStatus: INetworkStatus,
    private val cacheUserRepositoriesCache: IRoomGithubRepositoriesCache
) : GithubRepoInterface {

    override fun getUserRepoById(login: String, userId: Long): Single<List<UserRepoEntity>> {
        return networkStatus.statusSingle().flatMap { hasConnection ->
            if (hasConnection) {
                fetchRepoFromApi(login, true)
            } else {
                getFromDB(userId)
            }
        }
    }

    private fun getFromDB(userId: Long): Single<List<UserRepoEntity>> {
        return Single.fromCallable { repoDao.getRepoByUser(userId) }
            .map { it.map(RepoMapper::mapFromDbToRepo) }
    }

    override fun getForksRepoById(login: String, nameRepo: String): Single<List<ForkRepoEntity>> {
        return userApi.getForks(login, nameRepo).map { it.map(ForkMapper::mapToDetailForkEntity) }
    }

    override fun getForksByUrl(forkUrl: String): Single<List<ForkRepoEntity>> {
        return userApi.getForksByUrl(forkUrl).map { it.map(ForkMapper::mapToDetailForkEntity) }
    }

    private fun fetchRepoFromApi(
        login: String,
        shouldPersist: Boolean
    ): Single<List<UserRepoEntity>> {
        return userApi.getRepos(login)
            .doCompletableIf(shouldPersist) { listRepoDTO ->
//                Completable.create {
//                    repoDao.insert(listRepoDTO.map(RepoMapper::mapToRepoDB))
//                    it.onComplete()
//                }
                cacheUserRepositoriesCache.saveRepoCache(repoDao,listRepoDTO)
            }.map { it.map(RepoMapper::mapToRepoEntity) }
    }

}