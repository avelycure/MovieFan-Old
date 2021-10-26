package com.avelycure.moviefan.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.avelycure.moviefan.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.navController = Navigation.findNavController(this, R.id.fragment_container)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            val backStackSize = supportFragmentManager.findFragmentById(R.id.fragment_container)
                ?.childFragmentManager
                ?.backStackEntryCount
            if (backStackSize != null) {
                if (backStackSize > 0)
                    navController.popBackStack()
            }
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}