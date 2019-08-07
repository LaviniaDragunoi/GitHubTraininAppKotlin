package com.example.githubtraininappkotlin.owner

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
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
import androidx.navigation.fragment.findNavController
import com.example.githubtraininappkotlin.AppExecutors
import com.example.githubtraininappkotlin.DrawerLocker
import com.example.githubtraininappkotlin.R
import com.example.githubtraininappkotlin.Repository
import com.example.githubtraininappkotlin.Util
import com.example.githubtraininappkotlin.Util.AUTH_HEADER
import com.example.githubtraininappkotlin.Util.IS_LOGED
import com.example.githubtraininappkotlin.data.ApiClient
import com.example.githubtraininappkotlin.data.ApiInterface
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.databinding.FragmentUserBinding
import com.example.githubtraininappkotlin.models.OwnerEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: UserViewModel
    lateinit var mPreferences: SharedPreferences
    val sharedPrefFile = "com.example.githubtrainingappkotlin"
    lateinit var preferencesEditor: SharedPreferences.Editor
    var autheader: String? = null
    lateinit var emailAddress: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_user,
            container,
false
        )
        (activity as DrawerLocker).setDrawerLocked(false)
        (activity as AppCompatActivity).supportActionBar!!.apply {
            show()
            hasOptionsMenu()
        }
        initializeViewModel()
        binding.apply {
            userViewModel = viewModel
            lifecycleOwner = this@UserFragment
        }
        viewModel.owner.observe(this, Observer {
            if (it != null) {
                (activity as AppCompatActivity).supportActionBar!!.apply {
                    title = it.login
                    emailAddress = it.email!!
                }
                displayOwnerDetails(it)
            }
        })
        viewModel.viewReposAction.observe(this, Observer { showrepos ->
          viewReposList(showrepos)
        })
        viewModel.sendEmailAction.observe(this, Observer { it ->
            sendEmail(it)
        })

        return binding.root
    }

    fun displayOwnerDetails(ownerEntity: OwnerEntity) {
        Picasso.get().load(ownerEntity.avatarUrl).into(avatar)
        bioTextView.text = ownerEntity.bio
        locationTextView.text = ownerEntity.location
        emailTextView.text = ownerEntity.email
        createdDateTextView.text = Util.displayDate(ownerEntity.createdAt!!)
        updateDateTextView.text = Util.displayDate(ownerEntity.updatedAt!!)
        publicRepoNumber.text = ownerEntity.publicRepos.toString()
        privateRepoNumber.text = ownerEntity.totalPrivateRepos.toString()
    }

     fun sendEmail(sendEmailActivated: Boolean) {
         if (sendEmailActivated) {
             val intent = Intent(Intent.ACTION_SEND)
             intent.data = Uri.parse("mailto: ")
             intent.putExtra(Intent.EXTRA_EMAIL, emailAddress)
             intent.putExtra(Intent.EXTRA_SUBJECT, "Collaboration proposal")
             if (intent.resolveActivity(activity!!.packageManager) != null) {
                 activity!!.startActivity(intent)
             } else {
                 Toast.makeText(activity!!, "You don't have any email apps to contact us.", Toast.LENGTH_SHORT)
                     .show()
             }
         }
     }

    fun viewReposList(viewReposActiveted: Boolean) {
        if (viewReposActiveted) {
            this.findNavController().navigate(UserFragmentDirections.actionUserFragmentToReposFragment())
            viewModel.alreadyFetchedRepos()
        } else {
            viewModel.errorReposMessageLiveData.observe(this, Observer {
                Toast.makeText(activity!!, it, Toast.LENGTH_SHORT).show()
            })
        }
    }
    fun initializeViewModel() {
        mPreferences = activity!!.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        if (mPreferences.getBoolean(IS_LOGED, false)) {
            autheader = mPreferences.getString(AUTH_HEADER, null)
        }
        val application = requireNotNull(this.activity).application
        val database = AppDatabase.getInstance(application)
        val appExecutors = AppExecutors.instance
        val apiInterface = ApiClient.client.create(ApiInterface::class.java)
        val repository = Repository(database, appExecutors, apiInterface)
        val viewModelFactory = UserViewModelFactory(repository, autheader!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
    }
}