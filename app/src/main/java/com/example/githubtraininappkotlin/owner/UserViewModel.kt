package com.example.githubtraininappkotlin.owner

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.models.OwnerEntity

class UserViewModel(private val repository: Repository, application: Application) : ViewModel(){


    val owner : LiveData<OwnerEntity> = repository.getOwnerLDFromDb()
    private val _viewReposAction = MutableLiveData<Boolean>()
    val viewReposAction : LiveData<Boolean>
    get() = _viewReposAction
}