package com.example.githubtraininappkotlin

import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.githubtraininappkotlin.data.ApiClient
import com.example.githubtraininappkotlin.data.ApiInterface
import com.example.githubtraininappkotlin.database.AppDatabase
import com.example.githubtraininappkotlin.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DrawerLocker, NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
           R.id.created_action -> loadReposByCreatedDate()
            R.id.updated -> Toast.makeText(this, "Load repos by date updated", Toast.LENGTH_SHORT)
                .show()
            R.id.pushed -> Toast.makeText(this, "Load repos by date created", Toast.LENGTH_SHORT)
                .show()
            R.id.full_name -> Toast.makeText(this, "Load repos by date created", Toast.LENGTH_SHORT)
                .show()
            R.id.owner -> Toast.makeText(this, "Load repos by date created", Toast.LENGTH_SHORT)
                .show()
            R.id.collaborator -> Toast.makeText(this, "Load repos by date created", Toast.LENGTH_SHORT)
                .show()
            R.id.organization -> Toast.makeText(this, "Load repos by date created", Toast.LENGTH_SHORT)
                .show()
        }
        return true
    }

    override fun setDrawerLocked(lock: Boolean) {
       if (lock) {
           drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
       } else {
           drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
       }
    }

    private lateinit var viewModel: NavDrawerItemsViewModel
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var mPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var appBarConfiguration: AppBarConfiguration
    val sharedPrefFile = "com.example.githubtrainingappkotlin"
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        // Setting up the navigation drawer to the Activity
        drawerLayout = binding.drawer
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.isDrawerIndicatorEnabled
        nav_view.setNavigationItemSelectedListener(this)
        actionBarDrawerToggle.syncState()
        initViewModel()
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed() }
    }
    fun initViewModel() {
        val application = requireNotNull(this).application
        val database = AppDatabase.getInstance(application)
        val appExecutors = AppExecutors.instance
        val apiInterface = ApiClient.client.create(ApiInterface::class.java)
        val repository = Repository(database, appExecutors, apiInterface)
        val viewModelFactory = NavDrawerItemsViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NavDrawerItemsViewModel::class.java)
    }
    fun loadReposByCreatedDate() {}
}

