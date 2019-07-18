package com.example.githubtraininappkotlin.owner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.githubtraininappkotlin.DrawerLocker
import com.example.githubtraininappkotlin.R
import com.example.githubtraininappkotlin.databinding.FragmentUserBinding
import com.example.githubtraininappkotlin.models.OwnerEntity

class UserFragment: Fragment(){

    private lateinit var binding: FragmentUserBinding
    private lateinit var owner: OwnerEntity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user,
            container,
false
        )
        (activity as DrawerLocker).setDrawerLocked(false)
//        (activity as AppCompatActivity).supportActionBar!!.apply {
//           // setTitle(owner.login)
//        }
        return binding.root
    }
}