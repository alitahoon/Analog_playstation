package com.example.shoshaplaystation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.shoshaplaystation.R
import com.example.shoshaplaystation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.enter) // Specify the animation resource for enter animation
            .setExitAnim(R.anim.exit) // Specify the animation resource for exit animation
            .setPopEnterAnim(R.anim.pop_enter) // Specify the animation resource for pop enter animation
            .setPopExitAnim(R.anim.pop_exit) // Specify the animation resource for pop exit animation
            .build()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.addPlaystationReservation) as NavHostFragment
        val navController = navHostFragment.navController
        binding!!.mainBottomNavigation.setOnShowListener {
            // Handle item click here
            when (it.id) {
                1 -> {
                    Log.i(TAG, "invoice_icon2")

                }
                2 -> {
                    Log.i(TAG, "Home")
                }

                3 -> {
                    Log.i(TAG, "addPlaystationReservation")

                }
                // Add cases for other items if needed
            }
        }
        setBottomNavigationIconsIcons()
    }

    private fun setBottomNavigationIconsIcons() {
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.invoice_icon2))
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.home))
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.controller2))
        binding!!.mainBottomNavigation.show(2, true)
    }
}