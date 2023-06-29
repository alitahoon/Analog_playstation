package com.example.shoshaplaystation.ui
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.shoshaplaystation.R
import com.example.shoshaplaystation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }

        setBottomNavigationIconsIcons()
    }

    private fun setBottomNavigationIconsIcons() {
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.home))
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.invoice_icon2))
        binding!!.mainBottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.multi))
        binding!!.mainBottomNavigation.show(1,true)
    }
}