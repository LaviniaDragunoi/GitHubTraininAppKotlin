package com.example.githubtraininappkotlin

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.githubtraininappkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), DrawerLocker {
    override fun setDrawerLocked(lock: Boolean) {
       if (lock) {
           drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
       } else {
           drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
       }
    }
    lateinit var mPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    lateinit var drawerLayout: DrawerLayout
    lateinit var appBarConfiguration: AppBarConfiguration
    val sharedPrefFile = "com.example.githubtrainingappjava"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)
        // Setting up the navigation drawer to the Activity
        drawerLayout = binding.drawer
        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}
