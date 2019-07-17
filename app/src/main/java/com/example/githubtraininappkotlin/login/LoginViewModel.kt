package com.example.githubtraininappkotlin.login

import android.app.Application
import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraininappkotlin.Repository

class LoginViewModel(val repository: Repository, application: Application): ViewModel(){

    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()
    private val errorMessage = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String>
    get() = errorMessage
    private val _eventLoginAction = MutableLiveData<Boolean>()
    val eventLoginAction : LiveData<Boolean>
    get() =_eventLoginAction
    lateinit var authHeader:String

    fun isEmailValid(email: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun loginAction(){
        val base = "${userEmail.value}:${userPassword.value}"
         authHeader = "Basic " + Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP);
         getAuthentication(authHeader)

    }

    private fun getAuthentication(authHeader: String) {
       repository.fetchRetrofit(authHeader, {
           _eventLoginAction.postValue(true)

       }, {
           _eventLoginAction.postValue(false)
           errorMessage.postValue(it)

       }, {
           _eventLoginAction.postValue(false)
           errorMessage.postValue(it)
       })

    }

}