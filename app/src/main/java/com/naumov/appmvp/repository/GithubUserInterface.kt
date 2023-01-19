package com.naumov.appmvp.repository

import com.naumov.appmvp.model.ForkRepoEntity
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.model.UserRepoEntity
import com.naumov.appmvp.network.UserRepoDto
import io.reactivex.rxjava3.core.Single

interface GithubUserInterface {
    fun getUsersDefault():List<GithubUserEntity>
    fun getUserByid(login:String):Single<GithubUserEntity>
    fun getGithubUsers():Single<List<GithubUserEntity>>
    fun getUserWithRepos(login: String)
}