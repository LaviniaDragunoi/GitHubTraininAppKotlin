package com.example.githubtraininappkotlin.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubtraininappkotlin.Repository

class LoginViewModelFactory(
private val repository: Repository,private val application: Application
):ViewModelProvider.Factory{
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository, application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}