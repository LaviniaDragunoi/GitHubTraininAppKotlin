package com.example.githubtraininappkotlin.owner

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.models.GithubRepoEntity
import com.example.githubtraininappkotlin.models.OwnerEntity

class UserViewModel(
    private val repository: Repository,
    application: Application,
    private val authHeader: String
) : ViewModel() {

    val owner: LiveData<OwnerEntity> = repository.ownerLD
    val reposList: LiveData<List<GithubRepoEntity>> = repository.reposLd
    private val _viewReposAction = MutableLiveData<Boolean>()
    val viewReposAction: LiveData<Boolean>
    get() = _viewReposAction

    private val _sendEmailAction = MutableLiveData<Boolean>()
    val sendEmailAction: LiveData<Boolean>
        get() = _sendEmailAction

    private val errorReposMessage = MutableLiveData<String>()
    val errorReposMessageLiveData: LiveData<String>
        get() = errorReposMessage

    fun viewRepos() {
      getReposToDb(authHeader)
    }

    fun sendEmail() {
        if (owner.value!!.email!!.isNotEmpty()) {
            _sendEmailAction.postValue(true)
        } else _sendEmailAction.postValue(false)
    }

    fun getReposToDb(authHeader: String) {
        repository.fetchRetrofitRepos(authHeader,
            {
                _viewReposAction.postValue(true)
            }, {
                _viewReposAction.postValue(false)
                errorReposMessage.postValue(it)
            }
        )
    }
}