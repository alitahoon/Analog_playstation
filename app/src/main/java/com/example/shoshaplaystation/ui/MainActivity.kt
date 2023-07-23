package com.example.shoshaplaystation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.shoshaplaystation.R
import com.example.shoshaplaystation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val TAG = "MainActivity"
    var navController: NavController? = null
    var navOptions: NavOptions? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        binding!!.mainBottomNavigation.setOnShowListener {
            // Handle item click here
            when (it.id) {
                3 -> {
                    Log.i(TAG, "playstationReservations")
                    navigateTo("playstationReservations")

                }
                2 -> {
                    Log.i(TAG, "Home")
                    navigateTo("Home")
                }

                1 -> {
                    Log.i(TAG, "addPlaystationReservation")

                }
                // Add cases for other items if needed
            }
        }
        navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.enter) // Specify the animation resource for enter animation
            .setExitAnim(R.anim.exit) // Specify the animation resource for exit animation
            .setPopEnterAnim(R.anim.pop_enter) // Specify the animation resource for pop enter animation
            .setPopExitAnim(R.anim.pop_exit) // Specify the animation resource for pop exit animation
            .build()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView4) as NavHostFragment
        navController = navHostFragment.navController
        setBottomNavigationIconsIcons()
    }

    private fun setBottomNavigationIconsIcons() {
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.invoice_icon2))
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.home))
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.controller2))
        binding!!.mainBottomNavigation.show(2, true)
    }


    fun navigateTo(fragmentName: String) {
        when (fragmentName) {
            "Home" -> {
                navController!!.navigate(R.id.home2, null, navOptions)
            }
            "playstationReservations" -> {
                navController!!.navigate(R.id.currantReservation, null, navOptions)
            }
        }
    }
}