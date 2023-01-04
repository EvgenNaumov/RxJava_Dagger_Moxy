package com.naumov.appmvp.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(userDBEntity: UserDBEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun inserAll(userDBEntity: List<UserDBEntity>): Completable

    @Delete
    abstract fun delete(userDBEntity: UserDBEntity): Completable

    @Query("Select * From users")
    abstract fun getAllUser(): Single<List<UserDBEntity>>

    @Query("Select * FROM repos WHERE userId = :userId")
    abstract fun getAllReposByUser(userId: Long): List<RepoDBEntity>

    @Transaction
    @Query("Select * From users WHERE login = :login")
    abstract fun getUserWithRepos(login: String):Single<UserWithReposDB>
}