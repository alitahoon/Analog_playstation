package com.example.domain.repo

import Resource
import com.example.domain.entity.Device
import com.example.domain.entity.DeviceEntity

interface UserRepo {
    suspend fun insertDeviceToDatabase(
        device: DeviceEntity, result: (Resource<String>) -> Unit
    )

    suspend fun getDevicesFromDatabase(
        result: (Resource<ArrayList<DeviceEntity>>) -> Unit
    )
}