package com.example.domain.usecases

import Resource
import com.example.domain.entity.WatiedReservationsServicesEntity
import com.example.domain.repo.UserRepo

class InsertNewWaitedReservationItem(private val userRepo:UserRepo) {
    suspend operator fun invoke(watiedReservationsServicesEntity: WatiedReservationsServicesEntity,result: (Resource<String>) -> Unit)
    =userRepo.insertNewWaitedReservationItem(watiedReservationsServicesEntity,result)
}