package com.example.githubtraininappkotlin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubtraininappkotlin.Util.COLLABORATOR
import com.example.githubtraininappkotlin.Util.CREATED_AT
import com.example.githubtraininappkotlin.Util.FULL_NAME
import com.example.githubtraininappkotlin.Util.OWNER
import com.example.githubtraininappkotlin.Util.PUSHED_AT
import com.example.githubtraininappkotlin.Util.UPDATED_AT
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
            reposByCreatedDateMutableLd.postValue(database.ownerDatabaseDao.getRepoByCreatedDate())
            reposByUpdatedDateMutableLd.postValue(database.ownerDatabaseDao.getRepoByUpdatedDate())
            reposByPushedDateMutableLd.postValue(database.ownerDatabaseDao.getRepoByPushedDate())
            reposByFullNameMutableLd.postValue(database.ownerDatabaseDao.getRepoByFullName())
            reposOfOwnerMutableLd.postValue(database.ownerDatabaseDao.getRepoOfOwner(false))
            reposThatCollaboratsMutableLd.postValue(database.ownerDatabaseDao.getReposThatCollaborators())
        }
    }
    private val ownerMutableLD = MutableLiveData<OwnerEntity>()
    val ownerLD: LiveData<OwnerEntity>
    get() = ownerMutableLD
    private val reposMutableLD = MutableLiveData<List<GithubRepoEntity>>()
    val reposLd: LiveData<List<GithubRepoEntity>>
    get() = reposMutableLD
    private val reposByCreatedDateMutableLd = MutableLiveData<List<GithubRepoEntity>>()
    val reposByCreatedDate: LiveData<List<GithubRepoEntity>>
    get() = reposByCreatedDateMutableLd
    private val reposOfOwnerMutableLd = MutableLiveData<List<GithubRepoEntity>>()
    val reposOfOwner: LiveData<List<GithubRepoEntity>>
        get() = reposOfOwnerMutableLd
    private val reposByUpdatedDateMutableLd = MutableLiveData<List<GithubRepoEntity>>()
    val reposByUpdatedDate: LiveData<List<GithubRepoEntity>>
        get() = reposByUpdatedDateMutableLd
    private val reposByPushedDateMutableLd = MutableLiveData<List<GithubRepoEntity>>()
    val reposByPushedDate: LiveData<List<GithubRepoEntity>>
        get() = reposByPushedDateMutableLd
    private val repoDetailsMutableLD = MutableLiveData<GithubRepoEntity>()
    val repoDetailsLiveData: LiveData<GithubRepoEntity>
    get() = repoDetailsMutableLD
    private val reposByFullNameMutableLd = MutableLiveData<List<GithubRepoEntity>>()
    val reposByFullName: LiveData<List<GithubRepoEntity>>
    get() = reposByFullNameMutableLd
    private val reposThatCollaboratsMutableLd = MutableLiveData<List<GithubRepoEntity>>()
    val reposThatCollaborats: LiveData<List<GithubRepoEntity>>
        get() = reposThatCollaboratsMutableLd
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

    fun getRepoDetailsFromDbById(id: Int) {
        appExecutors.diskIO().execute {
            repoDetailsMutableLD.postValue(database.ownerDatabaseDao.getRepoById(id))
        }
    }

    fun getList(orderedBy: String?): LiveData<List<GithubRepoEntity>> {
        when (orderedBy) {
            CREATED_AT -> return reposByCreatedDate
            UPDATED_AT -> return reposByUpdatedDate
            PUSHED_AT -> return  reposByPushedDate
            FULL_NAME -> return reposByFullName
            OWNER -> return reposOfOwner
            COLLABORATOR -> return reposThatCollaborats
            else -> return reposLd
        }
    }
}
