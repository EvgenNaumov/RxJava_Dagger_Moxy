package com.naumov.appmvp.model

data class ForkRepoEntity(
    val id:Long,
    val name:String,
    val forkUrl:String,
    val description:String
)
