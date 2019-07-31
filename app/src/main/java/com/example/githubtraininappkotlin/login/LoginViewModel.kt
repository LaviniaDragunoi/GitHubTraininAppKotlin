package com.example.githubtraininappkotlin.login

import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.Util

class LoginViewModel(
    val repository: Repository
) : ViewModel() {
    val userEmail = MutableLiveData<String>()
    val userPassword = MutableLiveData<String>()
    private val errorMessage = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String>
    get() = errorMessage
    private val _eventLoginAction = MutableLiveData<Boolean>()
    val eventLoginAction
        get() = _eventLoginAction
    lateinit var authHeader: String
//    private var _isLoginActivated = MutableLiveData<Boolean>().apply {
//        isLoginButtonActivated()
//    }
//    val isLoginActivated : LiveData<Boolean>
//    get() = _isLoginActivated
    fun isEmailValid(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
//    fun isLoginButtonActivated(){
//        when{
//            (userEmail.value!!.isEmpty() || !isEmailValid(userEmail.value!!) || userPassword.value!!.isEmpty()) ->
//            _isLoginActivated.postValue(false)
//            (userEmail.value!!.isNotEmpty() && isEmailValid(userEmail.value!!) && userPassword.value!!.isNotEmpty())
//            -> _isLoginActivated.postValue(true)
//        }
//    }

    fun loginAction() {
        val base = "${Util.userName}:${Util.password}"
        authHeader = "Basic " + Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP)
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