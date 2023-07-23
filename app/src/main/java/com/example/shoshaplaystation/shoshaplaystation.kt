package com.example.shoshaplaystation

import android.app.Application
import com.example.shoshaplaystation.util.bindingMethods
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class shoshaplaystation: Application() {
    @Inject
    lateinit var bindingMethods:bindingMethods
}