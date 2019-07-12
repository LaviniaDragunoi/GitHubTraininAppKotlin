package com.example.githubtraininappkotlin.login

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.InverseMethod
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.githubtraininappkotlin.DrawerLocker
import com.example.githubtraininappkotlin.R
import com.example.githubtraininappkotlin.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        (activity as DrawerLocker).setDrawerLocked(true)
        (activity as AppCompatActivity).supportActionBar!!.hide()

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding .apply{
            loginViewModel = viewModel
            lifecycleOwner = this@LoginFragment
        }

        viewModel.eventLoginAction.observe( this, Observer { isLogedIn ->

            if(isLogedIn){
                Toast.makeText(activity!!,"user: "+ viewModel.userEmail.value.toString() + " Auth is : " +  viewModel.authHeader, Toast.LENGTH_LONG).show()
            }

        })

        return binding.root
    }

    }
