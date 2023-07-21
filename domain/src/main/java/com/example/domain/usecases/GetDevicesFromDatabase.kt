package com.example.domain.usecases

import Resource
import com.example.domain.entity.DeviceEntity
import com.example.domain.repo.UserRepo

class GetDevicesFromDatabase(private val userRepo: UserRepo) {
    suspend operator fun invoke(result: (Resource<ArrayList<DeviceEntity>>) -> Unit)=userRepo.getDevicesFromDatabase(result)
}