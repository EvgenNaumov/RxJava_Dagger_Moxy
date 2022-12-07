package com.naumov.appmvp.repository

import com.naumov.appmvp.model.GithubUserEntity

interface GithubInterface {
    fun getGithubUsers():List<GithubUserEntity>
}