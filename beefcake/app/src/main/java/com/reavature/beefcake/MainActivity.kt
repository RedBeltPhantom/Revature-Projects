package com.reavature.beefcake

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class  MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)


        //set drawer layout binding
        val binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)
        drawerLayout = binding.root.drawerLayout

        /**
         * This will inflate the Main Activity
         */


        /**
         * This will automatically take us to the home fragment that is set up in the Nav Graph
         */
        val navController =  findNavController(R.id.nav_host_fragment)

        val appConfig = AppBarConfiguration.Builder(setOf(R.id.mainMenu))
            .setDrawerLayout(drawerLayout)
            .build()

        NavigationUI.setupActionBarWithNavController(this, navController, appConfig)
        NavigationUI.setupWithNavController(navView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            bottom_navigation.visibility = if(destination.id == R.id.loginFragment || destination.id == R.id.registerFragment) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }

        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if(destination.id == R.id.loginFragment || destination.id == R.id.registerFragment)
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            else
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }

        bottom_navigation.setupWithNavController(navController)

        navView.menu.findItem(R.id.nav_logOut).setOnMenuItemClickListener {
            navController.navigate(R.id.global_navigate_to_login)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        navView.menu.findItem(R.id.nav_home).setOnMenuItemClickListener {
            navController.navigate(R.id.mainMenu)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        navView.menu.findItem(R.id.nav_workouts).setOnMenuItemClickListener {
            navController.navigate(R.id.muscleGroups)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        navView.menu.findItem(R.id.nav_equipments).setOnMenuItemClickListener {
            navController.navigate(R.id.equipmentList)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        navView.menu.findItem(R.id.nav_reserve).setOnMenuItemClickListener {
            navController.navigate(R.id.makeReservationFragment)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        navView.menu.findItem(R.id.nav_profile).setOnMenuItemClickListener {
            navController.navigate(R.id.healthProfile)
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        val appConfig = AppBarConfiguration.Builder(setOf(R.id.nav_home, R.id.mainMenu))
            .setDrawerLayout(drawerLayout)
            .build()
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, appConfig)
    }
}