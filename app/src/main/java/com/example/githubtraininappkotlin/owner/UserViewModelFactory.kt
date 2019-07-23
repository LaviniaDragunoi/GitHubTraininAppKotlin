package com.example.githubtraininappkotlin.owner

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubtraininappkotlin.Repository

class UserViewModelFactory(
    private val repository: Repository,
    private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}