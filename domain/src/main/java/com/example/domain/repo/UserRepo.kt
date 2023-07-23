package com.example.domain.repo

import Resource
import com.example.domain.entity.DeviceEntity
import com.example.domain.entity.PlaystationReservationEntity

interface UserRepo {
    suspend fun insertDeviceToDatabase(
        device: DeviceEntity, result: (Resource<String>) -> Unit
    )

    suspend fun getDevicesFromDatabase(
        result: (Resource<ArrayList<DeviceEntity>>) -> Unit
    )

    suspend fun deleteDeviceFromDatabaseByID(id:Long?,result: (Resource<String>) -> Unit)
    suspend fun getLastDeviceNumber(result: (Resource<Int>) -> Unit)
    suspend fun insertNewPlaystationReservation(playstationReservationEntity: PlaystationReservationEntity,result: (Resource<String>) -> Unit)
    suspend fun  getAllPlaystationReservations(result: (Resource<ArrayList<PlaystationReservationEntity>>) -> Unit)
    suspend fun updatePlaystationReservation(playstationReservationEntity: PlaystationReservationEntity,result: (Resource<String>) -> Unit)
}