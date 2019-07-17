package com.example.githubtraininappkotlin.login

import android.content.Context
import android.content.SharedPreferences
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.githubtraininappkotlin.*
import com.example.githubtraininappkotlin.data.ApiClient
import com.example.githubtraininappkotlin.data.ApiInterface
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private val IS_LOGED: String = "is_loged"
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    lateinit var mPreferences: SharedPreferences
    val sharedPrefFile = "com.example.githubtrainingappjava"
    lateinit var preferencesEditor: SharedPreferences.Editor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        mPreferences = this.activity!!.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        preferencesEditor = mPreferences.edit()
        (activity as DrawerLocker).setDrawerLocked(true)
        (activity as AppCompatActivity).supportActionBar!!.hide()
        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getInstance(application)
        val appExecutors = AppExecutors.instance
        val apiInterface =ApiClient.client.create(ApiInterface::class.java)
        val repository = Repository(database,appExecutors,apiInterface)
        val viewModelFactory = LoginViewModelFactory(repository,application)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)


        binding .apply{
            loginViewModel = viewModel
            lifecycleOwner = this@LoginFragment
        }


                viewModel.userEmail.observe(this, Observer {
            if(!viewModel.isEmailValid(it)){

                    userEmail.setError("Please enter a valid email address.")
            }

        })
        viewModel.userPassword.observe(this, Observer {
            if(it.isEmpty()){
                userPassword.setError("Please enter your password")
            }
        })


        viewModel.eventLoginAction.observe(this, Observer { isLoged ->
            if(isLoged){
                //Add sharePref with boolean that the user
                preferencesEditor.putBoolean(IS_LOGED, isLoged)
                preferencesEditor.apply()

            }else{
                preferencesEditor.putBoolean(IS_LOGED, isLoged)
                preferencesEditor.apply()
                Toast.makeText(activity!!,viewModel.errorMessageLiveData.value, Toast.LENGTH_SHORT).show()
            }
        })

        return binding.root
    }

    }
