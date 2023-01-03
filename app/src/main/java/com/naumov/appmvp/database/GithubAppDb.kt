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

    private fun create(contex:Context):GithubAppDb{
      return Room.databaseBuilder(
          contex,
          GithubAppDb::class.java,
          "giyhub.db"
      ).build()
    }
}