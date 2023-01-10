package com.naumov.appmvp.database

import androidx.room.*
import com.naumov.appmvp.model.UserRepoEntity
import com.naumov.appmvp.network.UserRepoDto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ReposDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos:List<RepoDBEntity>):Completable

    @Update
    fun update(repo:RepoDBEntity):Completable

    @Update
    fun update(vararg repo:RepoDBEntity):Completable

    @Update
    fun update(repos:List<RepoDBEntity>):Completable

    @Delete
    fun delete(repo:RepoDBEntity):Completable

    @Delete
    fun delete(vararg repo:RepoDBEntity):Completable

    @Delete
    fun delete(repos:List<RepoDBEntity>):Completable

    @Query ("SELECT * FROM repos")
    fun getAll():Single<List<RepoDBEntity>>

    @Query ("SELECT * FROM repos WHERE userId = :userId")
    fun getRepoByUser(userId:Long):Single<List<RepoDBEntity>>

    @Query("SELECT * FROM repos WHERE userId = :userId")
    fun getAllReposByUser(userId: Long): List<RepoDBEntity>
}
