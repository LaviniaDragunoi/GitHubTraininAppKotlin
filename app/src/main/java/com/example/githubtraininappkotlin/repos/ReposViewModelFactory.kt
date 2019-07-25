package com.example.githubtraininappkotlin.repos

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubtraininappkotlin.Repository

class ReposViewModelFactory (
    private val repository: Repository,
    private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ReposViewModel::class.java))
        {
            return ReposViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}