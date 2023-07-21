package com.example.shoshaplaystation.util

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setDeviceNumber")
fun setDeviceNumber(textView: TextView,deviceNumber:Int) {
  textView.setText("Device ${deviceNumber}")

}