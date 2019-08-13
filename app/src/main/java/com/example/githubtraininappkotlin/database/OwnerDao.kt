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
    @Query("SELECT * FROM repos_table ORDER BY created_at ASC")
    fun getRepoByCreatedDate(): List<GithubRepoEntity>
    @Query("SELECT * FROM repos_table ORDER BY pushed_at ASC")
    fun getRepoByPushedDate(): List<GithubRepoEntity>
    @Query("SELECT * FROM repos_table ORDER BY full_name ASC")
    fun getRepoByFullName(): List<GithubRepoEntity>
    @Query("SELECT * FROM repos_table ORDER BY updated_at DESC")
    fun getRepoByUpdatedDate(): List<GithubRepoEntity>

    @Query("SELECT * FROM repos_table WHERE fork = :value AND full_name LIKE '%LaviniaDragunoi%'")
    fun getRepoOfOwner(value: Boolean): List<GithubRepoEntity>

    @Query("SELECT * FROM repos_table WHERE full_name NOT LIKE '%LaviniaDragunoi%'")
    fun getReposThatCollaborators(): List<GithubRepoEntity>

    @Query("DELETE FROM owner_table")
    fun clearOwner()
    @Query("DELETE FROM repos_table")
    fun clearRepos()
    @Query("SELECT * FROM repos_table WHERE id = :id")
    fun getRepoById(id: Int): GithubRepoEntity
}