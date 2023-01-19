package com.naumov.appmvp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "repos",
    foreignKeys = [ForeignKey(
        entity = UserDBEntity::class,
        parentColumns = [UserDBEntity.PRIMARY_KEY],
        childColumns = [RepoDBEntity.FOREING_USER_KEY],
        onDelete = ForeignKey.CASCADE
    )]
)

data class RepoDBEntity(
    @PrimaryKey
    @ColumnInfo(name = PRIMARY_KEY)
    val id: Long,
    val login: String,
    val name: String,
    val forksUrl: String,
    val fullName: String,
    @ColumnInfo(name = FOREING_USER_KEY, index = true)
    val userId: Long
) {
    companion object {
        const val PRIMARY_KEY = "id"
        const val FOREING_USER_KEY = "userId"
    }
}

