package com.naumov.appmvp.network

import com.naumov.appmvp.model.GithubUserEntity
import dagger.Provides
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import javax.inject.Inject

interface UsersApi {

    @GET("/users")
    fun getAllUsers():Single<List<UserDto>>

    @GET("/users/{login}")
    fun getUser(@Path("login") login:String):Single<UserDto>

    //hw5
    @GET("/users/{login}/repos")
    fun getRepos(@Path("login") i:String):Single<List<UserRepoDto>>

    @GET("/users/{login}/{name}/forks")
    fun getForks(@Path("login") login:String, @Path("name") name:String):Single<List<ForkRepoDto>>

    @GET("")
    fun getForksByUrl(@Url url:String):Single<List<ForkRepoDto>>
}