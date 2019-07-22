package com.example.githubtraininappkotlin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubtraininappkotlin.data.ApiInterface
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.models.OwnerEntity
import java.lang.Exception


class Repository(
    private val database: AppDatabase,
    private val appExecutors: AppExecutors,
    private val apiInterface: ApiInterface
) {
    private val ownerMutableLD = MutableLiveData<OwnerEntity>()
    val ownerLD : LiveData<OwnerEntity>
    get() = ownerMutableLD

    fun fetchRetrofit(authHeader: String, onSuccess: (owner: OwnerEntity) -> Unit, onShowErrorToast: (message: String) -> Unit,
                      onInvalidUsernameAndPassword:(message:String) -> Unit) {
        appExecutors.networkIO().execute {
            try {
                val response = apiInterface.getOwner(authHeader).execute()
                    when (response.code()) {
                        200 ->  {
                            onSuccess(response.body()!!)
                            val owner = apiInterface.getOwner(authHeader).execute().body()
                            addOwnerToDb(owner!!)

                        }
                        401 -> onInvalidUsernameAndPassword("Invalid email or password")
                        404 -> onShowErrorToast("Something stupid happened")
                        else -> onShowErrorToast("Server did not respond with owner")
                    }

            } catch (exception: Exception) {
                Log.d("TAG",exception.message!!)
                onShowErrorToast("There is a connection error")
            }
        }

    }

    private fun addOwnerToDb(owner: OwnerEntity?) {
        database.ownerDatabaseDao.clearOwner()
        database.ownerDatabaseDao.insertOwner(owner!!)

    }
    init {
            appExecutors.diskIO().execute{
               ownerMutableLD.postValue(database.ownerDatabaseDao.getOwner())
            }
        }


}
