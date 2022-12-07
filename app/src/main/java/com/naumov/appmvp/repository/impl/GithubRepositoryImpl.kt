package com.naumov.appmvp.repository.impl

import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.repository.GithubInterface

class GithubRepositoryImpl: GithubInterface {

    private val listUsers:List<GithubUserEntity> = listOf(
        GithubUserEntity("login1"),
        GithubUserEntity("login2"),
        GithubUserEntity("login3"),
        GithubUserEntity("login4"),
    )

    override fun getGithubUsers():List<GithubUserEntity> = listUsers

}