package com.example.githubtraininappkotlin.owner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubtraininappkotlin.Repository

class UserViewModelFactory(
    private val repository: Repository,
    private val authHeader: String
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository, authHeader) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}