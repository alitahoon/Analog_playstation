package com.example.domain.usecases
import Resource
import com.example.domain.repo.UserRepo

class ClearPlaystationReservation(private val userRepo:UserRepo) {
    suspend operator fun invoke(result: (Resource<String>) -> Unit)=userRepo.clearPlaystationReservation(result)
}