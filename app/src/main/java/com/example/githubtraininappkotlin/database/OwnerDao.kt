package com.example.githubtraininappkotlin.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.githubtraininappkotlin.models.GithubRepoEntity
import com.example.githubtraininappkotlin.models.OwnerEntity

@Dao
interface OwnerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOwner(ownerDao: OwnerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReposList(reposList: List<GithubRepoEntity>)

    @Update
    fun updateReposList(reposList: List<GithubRepoEntity>)

    @Query("SELECT * FROM owner_table")
    fun getOwnerLiveData(): LiveData<OwnerEntity>

    @Query("SELECT * from owner_table")
    fun getOwner(): OwnerEntity

    @Query("SELECT * from repos_table")
    fun getReposList(): List<GithubRepoEntity>

    @Query("DELETE FROM owner_table")
    fun clearOwner()

    @Query("DELETE FROM repos_table")
    fun clearRepos()
}