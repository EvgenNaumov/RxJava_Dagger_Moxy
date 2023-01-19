package com.naumov.appmvp.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(userDBEntity: UserDBEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(userDBEntity: List<UserDBEntity>)

    @Delete
     fun delete(userDBEntity: UserDBEntity)

    @Query("SElECT * FROM users")
    fun getAllUser():List<UserDBEntity>



}