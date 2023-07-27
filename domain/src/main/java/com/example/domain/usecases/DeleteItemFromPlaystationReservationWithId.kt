package com.example.domain.usecases

import Resource
import com.example.domain.repo.UserRepo

class DeleteItemFromPlaystationReservationWithId (private val userRepo: UserRepo) {
    suspend operator fun invoke(id:Long?,result: (Resource<String>) -> Unit)=userRepo.deleteItemFromPlaystationReservationWithId(id,result)

}