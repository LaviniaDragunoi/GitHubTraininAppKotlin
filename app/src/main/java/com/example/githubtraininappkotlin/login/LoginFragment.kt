package com.example.githubtraininappkotlin.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.githubtraininappkotlin.AppExecutors
import com.example.githubtraininappkotlin.DrawerLocker
import com.example.githubtraininappkotlin.R
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.Util.AUTH_HEADER
import com.example.githubtraininappkotlin.Util.IS_LOGED
import com.example.githubtraininappkotlin.data.ApiClient
import com.example.githubtraininappkotlin.data.ApiInterface
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    lateinit var mPreferences: SharedPreferences
    private val sharedPrefFile = "com.example.githubtrainingappkotlin"
    lateinit var preferencesEditor: SharedPreferences.Editor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        initializeViewModel()
        mPreferences = this.activity!!.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        (activity as DrawerLocker).setDrawerLocked(true)
        (activity as AppCompatActivity).supportActionBar!!.hide()
        binding.apply {
            loginViewModel = viewModel
            lifecycleOwner = this@LoginFragment
        }
        viewModel.userEmail.observe(this, Observer {
            if (!viewModel.isEmailValid(it)) {
                userEmail.error = "Please enter a valid email address."
            }
        })
        viewModel.userPassword.observe(this, Observer {
            if (it.isEmpty()) {
                userPassword.error = "Please enter your password"
            }
        })
//     viewModel.isLoginActivated.observe(this, Observer {
//         loginButton.isEnabled = it
//        })
        viewModel.eventLoginAction.observe(this, Observer { isLoged ->
            if (isLoged) {
                // Add sharePref with boolean that the user
                preferencesEditor = mPreferences.edit()
                preferencesEditor.putBoolean(IS_LOGED, isLoged)
                preferencesEditor.putString(AUTH_HEADER, viewModel.authHeader)
                preferencesEditor.apply()
                view!!.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToUserFragment())
            } else {
                preferencesEditor.putBoolean(IS_LOGED, isLoged)
                preferencesEditor.apply()
                viewModel.errorMessageLiveData.observe(this, Observer {
                    Toast.makeText(activity!!, it, Toast.LENGTH_SHORT).show()
                })
            }
        })
        return binding.root
    }

    fun initializeViewModel() {
        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getInstance(application)
        val appExecutors = AppExecutors.instance
        val apiInterface = ApiClient.client.create(ApiInterface::class.java)
        val repository = Repository(database, appExecutors, apiInterface)
        val viewModelFactory = LoginViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
    }
}
