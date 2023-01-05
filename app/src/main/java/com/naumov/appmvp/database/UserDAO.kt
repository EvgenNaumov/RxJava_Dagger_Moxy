package com.naumov.appmvp.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(userDBEntity: UserDBEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun inserAll(userDBEntity: List<UserDBEntity>): Completable

    @Delete
     fun delete(userDBEntity: UserDBEntity): Completable

    @Query("SELECT * FROM users")
     fun getAllUser(): Single<List<UserDBEntity>>

    @Query("SELECT * FROM repos WHERE userId = :userId")
     fun getAllReposByUser(userId: Long): List<RepoDBEntity>

    @Transaction
    @Query("SELECT * From users WHERE login = :login")
     fun getUserWithRepos(login: String):Single<List<UserWithReposDB>>
}