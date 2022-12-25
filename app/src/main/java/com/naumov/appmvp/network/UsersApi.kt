package com.naumov.appmvp.network

import com.naumov.appmvp.model.GithubUserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {
    @GET("/users")
    fun getAllUsers():Single<List<UserDto>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login:String):Single<UserDto>

    //hw5
    @GET("/users/{login}/repos")
    fun getRepos(@Path("login") login:String):Single<UserDto>

}