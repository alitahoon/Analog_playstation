package com.example.domain.usercases

import Resource
import com.example.domain.repo.UserRepo

class DeleteDeviceFromDatabaseByID(private val userRepo: UserRepo) {
    suspend operator fun invoke(id:Long?,result: (Resource<String>) -> Unit)=userRepo.deleteDeviceFromDatabaseByID(id,result)
}