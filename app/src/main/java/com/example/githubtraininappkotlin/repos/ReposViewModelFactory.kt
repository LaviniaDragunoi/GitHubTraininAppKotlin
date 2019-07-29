package com.example.githubtraininappkotlin.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubtraininappkotlin.Repository

class ReposViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReposViewModel::class.java)) {
            return ReposViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}