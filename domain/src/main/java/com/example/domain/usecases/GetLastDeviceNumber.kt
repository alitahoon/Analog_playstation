package com.example.domain.usecases

import Resource
import com.example.domain.repo.UserRepo

class GetLastDeviceNumber(private val userRepo: UserRepo) {
    suspend operator fun invoke(result: (Resource<Int>) -> Unit)=userRepo.getLastDeviceNumber(result)
}