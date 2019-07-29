package com.example.githubtraininappkotlin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubtraininappkotlin.data.ApiInterface
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.models.GithubRepoEntity
import com.example.githubtraininappkotlin.models.OwnerEntity

class Repository(
    private val database: AppDatabase,
    private val appExecutors: AppExecutors,
    private val apiInterface: ApiInterface
) {
    init {
        appExecutors.diskIO().execute {
            ownerMutableLD.postValue(database.ownerDatabaseDao.getOwner())
            reposMutableLD.postValue(database.ownerDatabaseDao.getReposList())
        }
    }
    private val ownerMutableLD = MutableLiveData<OwnerEntity>()
    val ownerLD: LiveData<OwnerEntity>
    get() = ownerMutableLD
    private val reposMutableLD = MutableLiveData<List<GithubRepoEntity>>()
    val reposLd: LiveData<List<GithubRepoEntity>>
    get() = reposMutableLD
    fun fetchRetrofit(
        authHeader: String,
        onSuccess: (owner: OwnerEntity) -> Unit,
        onShowErrorToast: (message: String) -> Unit,
        onInvalidUsernameAndPassword: (message: String) -> Unit
    ) {
        appExecutors.networkIO().execute {
            try {
                val response = apiInterface.getOwner(authHeader).execute()
                    when (response.code()) {
                        200 -> {
                            onSuccess(response.body()!!)
                            val owner = apiInterface.getOwner(authHeader).execute().body()
                            addOwnerToDb(owner!!)
                        }
                        401 -> onInvalidUsernameAndPassword("Invalid email or password")
                        404 -> onShowErrorToast("Something stupid happened")
                        else -> onShowErrorToast("Server did not respond with owner")
                    }
            } catch (exception: Exception) {
                Log.d("TAG", exception.message!!)
                onShowErrorToast("There is a connection error")
            }
        }
    }
    fun fetchRetrofitRepos(
        authHeader: String,
        onSuccess: (repos: List<GithubRepoEntity>) -> Unit,
        onShowErrorToast: (message: String) -> Unit
    ) {
        appExecutors.networkIO().execute {
            try {
                val repoResponse = apiInterface.getRepos(authHeader).execute()
                when (repoResponse.code()) {
                    200 -> {
                        onSuccess(repoResponse.body()!!)
                        val repos = apiInterface.getRepos(authHeader).execute().body()
                        addReposToDb(repos)
                    }
                    404 -> onShowErrorToast("There was an error")
                    else -> onShowErrorToast("Server did not respond with owner")
                }
            } catch (exception: Exception) {
                Log.d("TAG", exception.message!!)
                onShowErrorToast("There is a connection error")
            }
        }
    }
    private fun addOwnerToDb(owner: OwnerEntity?) {
        database.ownerDatabaseDao.clearOwner()
        database.ownerDatabaseDao.insertOwner(owner!!)
    }
    private fun addReposToDb(reposList: List<GithubRepoEntity>?) {
        database.ownerDatabaseDao.clearRepos()
        database.ownerDatabaseDao.insertReposList(reposList!!)
    }
}
