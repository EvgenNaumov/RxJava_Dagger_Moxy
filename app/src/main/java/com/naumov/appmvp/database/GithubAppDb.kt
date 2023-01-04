package com.naumov.appmvp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(UserDBEntity::class),
            version =1
)
abstract class GithubAppDb:RoomDatabase() {

    abstract val userDAO: UserDAO

    companion object {
        private const val DB_NAME = "github.db"
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