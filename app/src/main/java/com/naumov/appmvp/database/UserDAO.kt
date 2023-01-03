package com.naumov.appmvp.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDBEntity: UserDBEntity):Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserAll(userDBEntity: List<UserDBEntity>):Completable

    @Delete
    fun delete(userDBEntity: UserDBEntity):Completable

    @Query("Select * From users")
    fun selestAllUser():Single<List<UserDBEntity>>


}