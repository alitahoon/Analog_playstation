package com.example.domain.usecases
import Resource
import com.example.domain.repo.UserRepo
import com.example.domain.entity.PlaystationReservationEntity

class UpdatePlaystationReservation(private val userRepo:UserRepo) {
    suspend operator fun invoke(playstationReservationEntity: PlaystationReservationEntity,
                                result: (Resource<String>) -> Unit)=userRepo.updatePlaystationReservation(playstationReservationEntity,result)
}