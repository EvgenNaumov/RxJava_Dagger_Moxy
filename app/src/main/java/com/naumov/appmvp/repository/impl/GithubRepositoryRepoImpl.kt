package com.naumov.appmvp.repository.impl

import com.naumov.appmvp.database.RepoDBEntity
import com.naumov.appmvp.database.ReposDAO
import com.naumov.appmvp.doCompletableIf
import com.naumov.appmvp.mapper.DboMapper
import com.naumov.appmvp.mapper.DboMapper.mapToDboEntity
import com.naumov.appmvp.mapper.ForkMapper
import com.naumov.appmvp.mapper.RepoMapper
import com.naumov.appmvp.mapper.UserMapper
import com.naumov.appmvp.model.ForkRepoEntity
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.model.UserRepoEntity
import com.naumov.appmvp.network.NetworkProvider.userApi
import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubRepoInterface
import io.reactivex.rxjava3.core.Single

class GithubRepositoryRepoImpl(
    userApi: UsersApi,
    private val repoDao: ReposDAO,
    private val networkStatus: Single<Boolean>
) : GithubRepoInterface {

    override fun getUserRepoById(login:String,userId: Long): Single<List<UserRepoEntity>> {
        return networkStatus.flatMap { hasConnection->
            if (hasConnection){
                fetchRepoFromApi(login, true)
            }else {
                getFromDB(userId)
            }
        }
    }

    private fun getFromDB(userId: Long): Single<List<UserRepoEntity>> {
        return repoDao.getRepoByUser(userId).map{ it.map(RepoMapper::mapFromDbToRepo) }
    }

    override fun getForksRepoById(login: String, nameRepo: String): Single<List<ForkRepoEntity>> {
        return userApi.getForks(login, nameRepo).map { it.map(ForkMapper::mapToDetailForkEntity) }
    }

    override fun getForksByUrl(forkUrl: String): Single<List<ForkRepoEntity>> {
        return userApi.getForksByUrl(forkUrl).map { it.map(ForkMapper::mapToDetailForkEntity) }
    }

    private fun fetchRepoFromApi(login:String, shouldPersist: Boolean): Single<List<UserRepoEntity>> {
        return userApi.getRepos(login)
            .doCompletableIf(shouldPersist){
                repoDao.insert(it.map(RepoMapper::mapToRepoDB))
            }.map{it.map(RepoMapper::mapToRepoEntity)}
    }

}