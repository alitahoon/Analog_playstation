package com.example.shoshaplaystation.ui

import Resource
import com.example.domain.entity.Device
import com.example.domain.entity.DeviceEntity

interface HomeView {
    fun getDevicesFromDatabase(result: Resource<ArrayList<DeviceEntity>>)
}