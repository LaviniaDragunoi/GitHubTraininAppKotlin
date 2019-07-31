package com.example.githubtraininappkotlin.repoDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.githubtraininappkotlin.AppExecutors
import com.example.githubtraininappkotlin.DrawerLocker
import com.example.githubtraininappkotlin.R
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.Util
import com.example.githubtraininappkotlin.data.ApiClient
import com.example.githubtraininappkotlin.data.ApiInterface
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.databinding.FragmentRepoDetailsBinding
import com.example.githubtraininappkotlin.models.GithubRepoEntity

class RepoDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRepoDetailsBinding
    private lateinit var viewModel: RepoDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_repo_details,
            container,
            false
        )
        initializeViewModel()
        (activity as DrawerLocker).setDrawerLocked(false)
        (activity as AppCompatActivity).supportActionBar!!.show()
        viewModel.repoDetails.observe(this, Observer {
            display(it)
        })
        return binding.root
    }
    fun initializeViewModel() {
        val application = requireNotNull(this.activity).application
        val arguments = RepoDetailsFragmentArgs.fromBundle(arguments!!)
        val repoId = arguments.id
        val database = AppDatabase.getInstance(application)
        val appExecutors = AppExecutors.instance
        val apiInterface = ApiClient.client.create(ApiInterface::class.java)
        val repository = Repository(database, appExecutors, apiInterface)
        val viewModelFactory = RepoDetailsViewModelFactory(repoId, repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(RepoDetailsViewModel::class.java)
    }
    fun display(repo: GithubRepoEntity) {
        binding.repoName.text = repo.name
        binding.repoFullName.text = repo.fullName
        binding.repoCreated.text = Util.displayDate(repo.createdAt.toString())
        binding.repoUpdated.text = Util.displayDate(repo.updatedAt.toString())
        binding.repoPushed.text = Util.displayDate(repo.pushedAt.toString())
        if (repo.mPrivate) {
            binding.repoPrivacy.text = "Private project"
        } else {
            binding.repoPrivacy.text = "Public project"
        }
    }
}