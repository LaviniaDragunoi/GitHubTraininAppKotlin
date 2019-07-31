package com.example.githubtraininappkotlin.repos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.githubtraininappkotlin.AppExecutors
import com.example.githubtraininappkotlin.DrawerLocker
import com.example.githubtraininappkotlin.R
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.data.ApiClient
import com.example.githubtraininappkotlin.data.ApiInterface
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.databinding.FragmentReposBinding

class ReposFragment : Fragment() {

    private lateinit var binding: FragmentReposBinding
    private lateinit var viewModel: ReposViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_repos,
            container,
            false
        )
        initializeViewModel()
        (activity as DrawerLocker).setDrawerLocked(false)
        (activity as AppCompatActivity).supportActionBar!!.apply {
            show()
            hasOptionsMenu()
            title = "Repositories list"
        }
        binding.apply {
            reposViewModel = viewModel
            lifecycleOwner = this@ReposFragment
        }
        val adapter = ReposAdapter(GithubRepoEntityListener { id ->
            viewModel.onRepoEntityClicked(id)
        })

        binding.reposRecycler.adapter = adapter
        binding.reposRecycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        viewModel.reposList.observe(this, Observer {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
            }
        })
        viewModel.navigateToRepoDetails.observe(this, Observer { repo ->
            repo?.let {
                this.findNavController()
                    .navigate(ReposFragmentDirections.actionReposFragmentToRepoDetailsFragment(repo))
                viewModel.onRepoEntityNavigated()
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
        val viewModelFactory = ReposViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReposViewModel::class.java)
    }
}

