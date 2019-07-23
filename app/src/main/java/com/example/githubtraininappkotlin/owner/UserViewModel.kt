package com.example.githubtraininappkotlin.owner

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.models.GithubRepoEntity
import com.example.githubtraininappkotlin.models.OwnerEntity

class UserViewModel(private val repository: Repository, application: Application) : ViewModel(){


    val owner : LiveData<OwnerEntity> = repository.ownerLD
    val reposList: LiveData<List<GithubRepoEntity>> = repository.reposLd
    private val _viewReposAction = MutableLiveData<Boolean>()
    val viewReposAction: LiveData<Boolean>
    get() = _viewReposAction

    private val _sendEmailAction = MutableLiveData<Boolean>()
    val sendEmailAction: LiveData<Boolean>
        get() = _sendEmailAction


    fun viewRepos(){
       if(reposList.value!!.isNotEmpty()){
           _viewReposAction.postValue(true)
       }else {
           _viewReposAction.postValue(false)
       }
    }


    fun sendEmail(){
        if (owner.value!!.email!!.isNotEmpty()){
            _sendEmailAction.postValue(true)
        }else _sendEmailAction.postValue(false)
    }
}