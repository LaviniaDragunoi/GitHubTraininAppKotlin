package com.example.githubtraininappkotlin.repoDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubtraininappkotlin.Repository

class RepoDetailsViewModelFactory(
    private val repoId: Int,
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepoDetailsViewModel::class.java)) {
            return RepoDetailsViewModel(repoId, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class ")
    }
}