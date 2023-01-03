package com.naumov.appmvp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserDBEntity(
    @PrimaryKey
    val id: Long,
    val login: String,
    val avatarUrl:String
)
