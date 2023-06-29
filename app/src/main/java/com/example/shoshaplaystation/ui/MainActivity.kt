package com.example.shoshaplaystation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
                    Log.i(TAG, "multi")

                }
                // Add cases for other items if needed
            }
        }
        setBottomNavigationIconsIcons()
    }

    private fun setBottomNavigationIconsIcons() {
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.invoice_icon2))
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.home))
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.multi))
        binding!!.mainBottomNavigation.show(2, true)
    }
}