package com.example.shoshaplaystation.ui

import Resource
import com.example.domain.entity.Device

interface HomeContracts {
    interface Presenter {
        fun getDevicesFromDatabase(resultresult: (Resource<Device>) -> Unit)
        fun addDeviceToDatabase(device: Device,result: (Resource<String>) -> Unit)
    }
}