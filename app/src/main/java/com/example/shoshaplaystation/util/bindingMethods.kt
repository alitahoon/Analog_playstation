package com.example.shoshaplaystation.util

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setDeviceNumber")
fun setDeviceNumber(textView: TextView,number:Int?) {
  textView.setText("Device ${number}")
}