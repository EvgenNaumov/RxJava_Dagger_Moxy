package com.naumov.appmvp.repository

import com.naumov.appmvp.database.ReposDAO
import com.naumov.appmvp.database.UserDAO
import com.naumov.appmvp.network.UserDto
import com.naumov.appmvp.network.UserRepoDto
import io.reactivex.rxjava3.core.Completable

interface IRoomGithubRepositoriesCache {
    fun saveRepoCache(repoDao: ReposDAO, listRepoDTO:List<UserRepoDto>):Completable
}