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
import com.example.githubtraininappkotlin.Util.COLLABORATOR
import com.example.githubtraininappkotlin.Util.CREATED_AT
import com.example.githubtraininappkotlin.Util.FULL_NAME
import com.example.githubtraininappkotlin.Util.OWNER
import com.example.githubtraininappkotlin.Util.PUSHED_AT
import com.example.githubtraininappkotlin.Util.UPDATED_AT
import com.example.githubtraininappkotlin.data.ApiClient
import com.example.githubtraininappkotlin.data.ApiInterface
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.databinding.FragmentReposBinding

class ReposFragment : Fragment() {

    private var orderedBy: String? = null
    private lateinit var binding: FragmentReposBinding
    private lateinit var viewModel: ReposViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_repos,
            container,
            false
        )
        initViewModel()
        (activity as DrawerLocker).setDrawerLocked(false)
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

    fun initViewModel() {
        val application = requireNotNull(this.activity).application
        val arguments = ReposFragmentArgs.fromBundle(arguments!!)
        orderedBy = arguments.orderedBy
        var toolbarTitleSet: String
        when (orderedBy) {
            CREATED_AT -> toolbarTitleSet = getString(R.string.sorted_by_created_date_string)
            UPDATED_AT -> toolbarTitleSet = getString(R.string.updated_darte_string)
            PUSHED_AT -> toolbarTitleSet = getString(R.string.pushed_at_string)
            FULL_NAME -> toolbarTitleSet = getString(R.string.full_name_string)
            OWNER -> toolbarTitleSet = getString(R.string.owned_repos_string)
            COLLABORATOR -> toolbarTitleSet = getString(R.string.collaborators_string)
            else -> toolbarTitleSet = getString(R.string.repositories_list_string)
            }
            (activity as AppCompatActivity).supportActionBar!!.apply {
                show()
                hasOptionsMenu()
                title = toolbarTitleSet
            }
            val database = AppDatabase.getInstance(application)
            val appExecutors = AppExecutors.instance
            val apiInterface = ApiClient.client.create(ApiInterface::class.java)
            val repository = Repository(database, appExecutors, apiInterface)
            val viewModelFactory = ReposViewModelFactory(repository, orderedBy)
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(ReposViewModel::class.java)
        }
}

