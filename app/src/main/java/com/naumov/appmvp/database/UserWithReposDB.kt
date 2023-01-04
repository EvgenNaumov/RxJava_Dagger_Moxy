package com.naumov.appmvp.database

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithReposDB(
    @Embedded
    val userDBEntity:UserDBEntity,
    @Relation(
        parentColumn = UserDBEntity.PRIMARY_KEY,
        entityColumn = RepoDBEntity.FOREING_USER_KEY
    )
    val repos:List<RepoDBEntity>
)

