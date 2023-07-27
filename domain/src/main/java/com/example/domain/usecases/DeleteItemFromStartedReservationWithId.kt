package com.example.domain.usecases

import Resource
import com.example.domain.repo.UserRepo

class DeleteItemFromStartedReservationWithId (private val userRepo: UserRepo) {
    suspend operator fun invoke(id:Long?,result: (Resource<String>) -> Unit)=userRepo.deleteItemFromStartedReservationWithId(id,result)

}