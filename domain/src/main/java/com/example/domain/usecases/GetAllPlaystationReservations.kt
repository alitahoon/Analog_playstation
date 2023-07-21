package com.example.domain.usecases
import Resource
import com.example.domain.entity.PlaystationReservationEntity
import com.example.domain.repo.UserRepo
class GetAllPlaystationReservations(private val userRepo:UserRepo) {
    suspend operator fun invoke(result: (Resource<ArrayList<PlaystationReservationEntity>>) -> Unit)=userRepo.getAllPlaystationReservations(result)
}