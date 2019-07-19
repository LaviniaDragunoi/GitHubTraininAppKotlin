package com.example.githubtraininappkotlin.owner

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.githubtraininappkotlin.AppExecutors
import com.example.githubtraininappkotlin.DrawerLocker
import com.example.githubtraininappkotlin.R
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.data.ApiClient
import com.example.githubtraininappkotlin.data.ApiInterface
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.databinding.FragmentUserBinding
import com.example.githubtraininappkotlin.models.OwnerEntity

class UserFragment: Fragment(){

    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user,
            container,
false
        )
        (activity as DrawerLocker).setDrawerLocked(false)

        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getInstance(application)
        val appExecutors = AppExecutors.instance
        val apiInterface = ApiClient.client.create(ApiInterface::class.java)
        val repository = Repository(database,appExecutors,apiInterface)
        val viewModelFactory = UserViewModelFactory(repository,application)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(UserViewModel::class.java)

        binding .apply{
            userViewModel = viewModel
            lifecycleOwner = this@UserFragment
        }

        viewModel.owner.observe(this, Observer {
            if(it != null){
               Log.d("TAG", it.login)
            }

        })
//        (activity as AppCompatActivity).supportActionBar!!.apply {
//            setTitle(ownerEntity.login)
//        }
        return binding.root
    }
}