package com.naumov.appmvp.model

data class UserRepoEntity(
    val id:Long,
    val login:String,
    val name:String,
    val forksUrl:String,
    val fullName:String
)
