package com.example.domain.usecases

import Resource
import com.example.domain.entity.StartedReservationsServicesEntity
import com.example.domain.repo.UserRepo

class InsertNewStartedReservationItem(private val userRepo:UserRepo) {
    suspend operator fun invoke(startedReservationsServicesEntity: StartedReservationsServicesEntity, result: (Resource<String>) -> Unit)
            =userRepo.insertNewStartedReservationItem(startedReservationsServicesEntity,result)
}