package com.example.shoshaplaystation.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.shoshaplaystation.R
import com.example.shoshaplaystation.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class splash : AppCompatActivity() {
    private var binding:ActivitySplashBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        // Scale from 0% to 100%
        val scaleXAnimator = ObjectAnimator.ofFloat(binding!!.splashLogo, "scaleX", 0f, 1f)
        val scaleYAnimator = ObjectAnimator.ofFloat(binding!!.splashLogo, "scaleY", 0f, 1f)

        // Set duration in milliseconds (3 seconds)
        scaleXAnimator.duration = 2000
        scaleYAnimator.duration = 2000

        // Add an AnimatorListener to perform an action when the animation finishes
        scaleXAnimator.addListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                // Example: Moving from MainActivity to SecondActivity
                val intent = Intent(this@splash, MainActivity::class.java)
                startActivity(intent)

            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })

        // Start the animation
        scaleXAnimator.start()
        scaleYAnimator.start()
    }
    }
