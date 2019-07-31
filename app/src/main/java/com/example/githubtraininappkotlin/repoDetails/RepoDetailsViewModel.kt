package com.example.githubtraininappkotlin.repoDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.models.GithubRepoEntity

class RepoDetailsViewModel(
    private val repoId: Int,
    private val repository: Repository
) : ViewModel() {
    val repoDetails: LiveData<GithubRepoEntity>
    get() {
        repository.getRepoDetailsFromDbById(repoId)
        return repository.repoDetailsLiveData
    }
}