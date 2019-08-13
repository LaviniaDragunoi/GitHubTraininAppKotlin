package com.example.githubtraininappkotlin.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.models.GithubRepoEntity
import com.example.githubtraininappkotlin.models.OwnerEntity

class ReposViewModel(
    private val repository: Repository,
    private var orderedBy: String?
) : ViewModel() {
    val owner: LiveData<OwnerEntity> = repository.ownerLD
    val reposList: LiveData<List<GithubRepoEntity>> = repository.getList(orderedBy)
    private val _navigateToRepoDetails = MutableLiveData<Int>()
    val navigateToRepoDetails
    get() = _navigateToRepoDetails

    fun onRepoEntityClicked(id: Int) {
        _navigateToRepoDetails.value = id
    }

    fun onRepoEntityNavigated() {
        _navigateToRepoDetails.value = null
    }
}