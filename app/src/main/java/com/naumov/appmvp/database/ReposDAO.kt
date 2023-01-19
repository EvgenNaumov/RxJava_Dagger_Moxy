package com.naumov.appmvp.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ReposDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repos: List<RepoDBEntity>)

    @Update
    fun update(repo: RepoDBEntity)

    @Update
    fun update(vararg repo: RepoDBEntity)

    @Update
    fun update(repos: List<RepoDBEntity>)

    @Delete
    fun delete(repo: RepoDBEntity)

    @Delete
    fun delete(vararg repo: RepoDBEntity)

    @Delete
    fun delete(repos: List<RepoDBEntity>)

    @Query("SELECT * FROM repos")
    fun getAll(): List<RepoDBEntity>

    @Query("SELECT * FROM repos WHERE userId = :userId")
    fun getRepoByUser(userId: Long): List<RepoDBEntity>

    @Query("SELECT * FROM repos WHERE userId = :userId")
    fun getAllReposByUser(userId: Long): List<RepoDBEntity>
}
