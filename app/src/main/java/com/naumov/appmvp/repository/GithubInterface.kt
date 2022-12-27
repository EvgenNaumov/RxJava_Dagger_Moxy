package com.naumov.appmvp.repository

import com.naumov.appmvp.model.GithubUserEntity
import io.reactivex.rxjava3.core.Single

interface GithubInterface {
    fun getUsersDefault():List<GithubUserEntity>
    fun getUserByid(login:String):Single<GithubUserEntity>
    fun getGithubUsers():Single<List<GithubUserEntity>>
}