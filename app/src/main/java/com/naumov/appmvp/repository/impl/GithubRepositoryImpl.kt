package com.naumov.appmvp.repository.impl

import com.naumov.appmvp.mapper.UserMapper
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.network.UserDto
import com.naumov.appmvp.network.UsersApi
import com.naumov.appmvp.repository.GithubInterface
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import java.util.*

class GithubRepositoryImpl(private val userApi:UsersApi ): GithubInterface{

    private val listUsers:List<GithubUserEntity> = listOf(
        GithubUserEntity("Ivan","some text about Ivan",""),
        GithubUserEntity("Mr. Jon","some text about Mr. Jon",""),
        GithubUserEntity("Peter Pen","some text about Peter Pen",""),
        GithubUserEntity("Klaus","some text about Klaus",""),
    )

    override fun getGithubUsers(): Single<List<GithubUserEntity>> {
        return userApi.getAllUsers().map { it.map(UserMapper::mapToEntity) }
    }

    override fun getUserByid(login: String): Single<GithubUserEntity> {
        return userApi.getUser(login).map(UserMapper::mapToEntity)
    }
}