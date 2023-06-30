package com.example.shoshaplaystation.ui

import Resource
import com.example.domain.entity.Device

interface AddDeviceView {

    fun addDeviceToDatabase(result: Resource<String>)
    fun getLastDeviceNumber(result: Resource<Int>)

}