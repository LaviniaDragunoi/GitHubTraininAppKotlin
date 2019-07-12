package com.example.githubtraininappkotlin.login

import android.util.Base64
import android.widget.Toast
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel(){

    val userEmail = MutableLiveData<String>()

    lateinit var authHeader: String
    private val _userEmailDisplayed = MutableLiveData<String>()
    val userEmailDisplayed: LiveData<String>
    get() = _userEmailDisplayed


    val userPassword = MutableLiveData<String>()

    private val _userPasswordDisplayed = MutableLiveData<String>()
    val userPasswordDisplayed: LiveData<String>
    get() = _userPasswordDisplayed

    private val _eventLoginAction = MutableLiveData<Boolean>()
    val eventLoginAction : LiveData<Boolean>
    get() =_eventLoginAction

    fun loginAction(){
        val base = "${userEmail.value}:${userPassword.value}"
         authHeader = "Basic " + Base64.encodeToString(base.toByteArray(), Base64.NO_WRAP);
        _eventLoginAction.value = true
    }

}