package com.yuri.apprickmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private var navControllerCallback: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navControllerCallback = findNavController(R.id.navHostFragment)

        setupActionBarWithNavController(navControllerCallback!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navControllerCallback!!.navigateUp() || super.onSupportNavigateUp()
    }

}