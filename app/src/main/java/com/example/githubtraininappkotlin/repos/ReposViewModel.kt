package com.example.githubtraininappkotlin.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.models.GithubRepoEntity
import com.example.githubtraininappkotlin.models.OwnerEntity

class ReposViewModel(
    private val repository: Repository
) : ViewModel() {
    val owner: LiveData<OwnerEntity> = repository.ownerLD
    val reposList: LiveData<List<GithubRepoEntity>> = repository.reposLd
}