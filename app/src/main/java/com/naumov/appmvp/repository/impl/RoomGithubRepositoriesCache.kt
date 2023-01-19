package com.naumov.appmvp.repository.impl

import com.naumov.appmvp.database.ReposDAO
import com.naumov.appmvp.mapper.RepoMapper
import com.naumov.appmvp.network.UserRepoDto
import com.naumov.appmvp.repository.IRoomGithubRepositoriesCache
import io.reactivex.rxjava3.core.Completable

class RoomGithubRepositoriesCache : IRoomGithubRepositoriesCache {
    override fun saveRepoCache(repoDao: ReposDAO, listRepoDTO: List<UserRepoDto>): Completable {
        return Completable.create {
            repoDao.insert(listRepoDTO.map(RepoMapper::mapToRepoDB))
            it.onComplete()
        }

    }
}