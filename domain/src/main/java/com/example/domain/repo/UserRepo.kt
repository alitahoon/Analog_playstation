package com.example.domain.repo

import Resource
import com.example.domain.entity.DeviceEntity
import com.example.domain.entity.PlaystationReservationEntity
import com.example.domain.entity.StartedReservationsServicesEntity
import com.example.domain.entity.WatiedReservationsServicesEntity

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
    suspend fun insertNewStartedReservationItem(startedReservationsServicesEntity: StartedReservationsServicesEntity,result: (Resource<String>) -> Unit)
    suspend fun insertNewWaitedReservationItem(watiedReservationsServicesEntity: WatiedReservationsServicesEntity,result: (Resource<String>) -> Unit)
    suspend fun  getAllPlaystationReservations(result: (Resource<ArrayList<PlaystationReservationEntity>>) -> Unit)
    suspend fun  getAllStartedPlaystationReservations(result: (Resource<ArrayList<StartedReservationsServicesEntity>>) -> Unit)
    suspend fun  getAllWaitedPlaystationReservations(result: (Resource<ArrayList<WatiedReservationsServicesEntity>>) -> Unit)
    suspend fun deleteItemFromStartedReservationWithId(id:Long?,result: (Resource<String>) -> Unit)
    suspend fun deleteItemFromPlaystationReservationWithId(id:Long?,result: (Resource<String>) -> Unit)
    suspend fun deleteItemFromWaitedReservationWithId(id:Long?,result: (Resource<String>) -> Unit)
    suspend fun updatePlaystationReservation(playstationReservationEntity: PlaystationReservationEntity,result: (Resource<String>) -> Unit)
    suspend fun clearPlaystationReservation(result: (Resource<String>) -> Unit)
}