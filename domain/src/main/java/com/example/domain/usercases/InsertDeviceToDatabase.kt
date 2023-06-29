package com.example.domain.usercases

import Resource
import com.example.domain.entity.Device
import com.example.domain.entity.DeviceEntity
import com.example.domain.repo.UserRepo

class InsertDeviceToDatabase(private val userRepo: UserRepo) {
    suspend operator fun invoke(device: DeviceEntity, result: (Resource<String>) -> Unit
    )=userRepo.insertDeviceToDatabase(device,result)
}