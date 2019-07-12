package com.example.githubtraininappkotlin.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.githubtraininappkotlin.DrawerLocker
import com.example.githubtraininappkotlin.R
import com.example.githubtraininappkotlin.databinding.FragmentReposBinding
import java.security.acl.Owner

class ReposFragment: Fragment(){

  private lateinit var binding: FragmentReposBinding
    lateinit var owner: Owner

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_repos,
            container,
            false
        )

        (activity as DrawerLocker).setDrawerLocked(false)

        return binding.root

    }
}

