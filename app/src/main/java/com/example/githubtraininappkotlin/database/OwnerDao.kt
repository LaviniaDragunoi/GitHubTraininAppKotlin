package com.example.githubtraininappkotlin.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.githubtraininappkotlin.models.GithubRepoEntity
import com.example.githubtraininappkotlin.models.OwnerEntity

@Dao
interface OwnerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOwner(ownerDao: OwnerEntity)

    @Insert
    fun insertReposList(reposList: List<GithubRepoEntity>)

    @Update
    fun updateReposList(reposList: List<GithubRepoEntity>)

    @Query("SELECT * from owner_table")
    fun getOwnerLiveData(): LiveData<OwnerEntity>

    @Query("SELECT * from owner_table")
    fun getOwner(): OwnerEntity

    @Query("SELECT * from repos_table")
    fun getReposListLiveData(): LiveData<List<GithubRepoEntity>>

    @Query("DELETE FROM owner_table")
    fun clearOwner()

    @Query("DELETE FROM repos_table")
    fun clearRepos()
}