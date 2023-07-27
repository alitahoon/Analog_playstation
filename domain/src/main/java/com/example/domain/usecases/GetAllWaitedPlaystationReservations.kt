package com.example.domain.usecases

import Resource
import com.example.domain.entity.WatiedReservationsServicesEntity
import com.example.domain.repo.UserRepo

class GetAllWaitedPlaystationReservations (private val userRepo: UserRepo){
    suspend operator fun invoke(result: (Resource<ArrayList<WatiedReservationsServicesEntity>>) -> Unit)=userRepo.getAllWaitedPlaystationReservations (result)
}