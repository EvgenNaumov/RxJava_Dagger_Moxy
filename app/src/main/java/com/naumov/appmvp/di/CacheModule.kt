package com.naumov.appmvp.di

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naumov.appmvp.App
import com.naumov.appmvp.database.GithubAppDb
import com.naumov.appmvp.repository.IRoomGithubUsersCache
import com.naumov.appmvp.repository.impl.RoomGithubUsersCache
import dagger.Module
import dagger.Provides
import okhttp3.internal.Util
import javax.inject.Named
import javax.inject.Singleton

@Module
class CacheModule {

    @Named("dababase")
    @Provides
    fun nameDB():String = "github.db"

    @Singleton
    @Provides
    fun database(app:App,@Named("database") nameDB:String):RoomDatabase = Room.databaseBuilder(app, GithubAppDb::class.java!!, nameDB).build()

    @Singleton
    @Provides
    fun usersCache(database: Database):IRoomGithubUsersCache = RoomGithubUsersCache()
}