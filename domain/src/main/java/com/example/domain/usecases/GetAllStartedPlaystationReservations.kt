package com.example.domain.usecases

import Resource
import com.example.domain.entity.StartedReservationsServicesEntity
import com.example.domain.repo.UserRepo

class GetAllStartedPlaystationReservations (private val userRepo: UserRepo) {
    suspend operator fun invoke(result: (Resource<ArrayList<StartedReservationsServicesEntity>>) -> Unit)=userRepo.getAllStartedPlaystationReservations(result)

}