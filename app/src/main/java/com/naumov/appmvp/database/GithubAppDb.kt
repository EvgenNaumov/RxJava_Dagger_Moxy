package com.naumov.appmvp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

const val DB_NAME = "github.db"

@Database(
    entities = [UserDBEntity::class, RepoDBEntity::class],
            version =1
)

abstract class GithubAppDb:RoomDatabase() {

    abstract val userDAO: UserDAO
    abstract val repoDAO: ReposDAO


    companion object {

        private var instance: GithubAppDb? = null

        fun getInstance() = instance ?: throw RuntimeException(
            "Database has not been created." +
                    "Please call create(context)"
        )

        fun create(contex: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    contex!!,
                    GithubAppDb::class.java,
                    DB_NAME
                ).build()
            }

        }
    }
}