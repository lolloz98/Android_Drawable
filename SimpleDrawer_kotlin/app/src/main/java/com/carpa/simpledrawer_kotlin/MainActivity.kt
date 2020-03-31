package com.carpa.simpledrawer_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.carpa.simpledrawer_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Instead of:
        //setContentView(R.layout.activity_main)
        //we use data binding: a way to tell the app where to find views at compile time.
        //In this case it would have been best to use view binding https://developer.android.com/topic/libraries/view-binding.
        //Since it is a simple tutorial I'd preferred to show how to set up data binding.
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        navController = this.findNavController(R.id.myNavHostFrag)
        drawerLayout = binding.drawerLayout
        binding.navView.setupWithNavController(navController)
        //If we wanted we could have used another tool bar. But we are good with the default :)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        //To lock the drawer just to the start fragment:
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // if we did not have a drawer: return navController.navigateUp()
        //Drawer handling the stack:
        //If you are in one fragment and you have navigated into it through the drawer, the back button
        //will take you to the start fragment (every other fragment above it is automatically popped)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
