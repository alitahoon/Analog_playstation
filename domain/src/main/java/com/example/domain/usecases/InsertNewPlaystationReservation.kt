package com.example.domain.usecases

import Resource
import com.example.domain.entity.PlaystationReservationEntity
import com.example.domain.repo.UserRepo

class InsertNewPlaystationReservation(private val userRepo: UserRepo) {
    suspend operator fun invoke(
        playstationReservationEntity: PlaystationReservationEntity,
        result: (Resource<String>) -> Unit
    ) = userRepo.insertNewPlaystationReservation(playstationReservationEntity,result)
}