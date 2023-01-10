package com.naumov.appmvp.repository

import com.naumov.appmvp.model.ForkRepoEntity
import com.naumov.appmvp.model.UserRepoEntity
import io.reactivex.rxjava3.core.Single

interface GithubRepoInterface {
    fun getUserRepoById(login:String, userId: Long): Single<List<UserRepoEntity>>
    fun getForksRepoById(login:String, nameRepo:String): Single<List<ForkRepoEntity>>
    fun getForksByUrl(forkUrl:String): Single<List<ForkRepoEntity>>
}