package com.example.githubtraininappkotlin.repoDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.githubtraininappkotlin.DrawerLocker
import com.example.githubtraininappkotlin.R
import com.example.githubtraininappkotlin.databinding.FragmentRepoDetailsBinding

class RepoDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRepoDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_repo_details,
            container,
            false
        )

        (activity as DrawerLocker).setDrawerLocked(true)
        (activity as AppCompatActivity).supportActionBar!!.show()

        return binding.root
    }
}