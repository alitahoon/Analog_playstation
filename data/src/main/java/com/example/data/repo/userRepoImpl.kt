package com.example.data.repo

import Resource
import com.example.data.MyDatabase
import com.example.domain.entity.DeviceEntity
import com.example.domain.entity.PlaystationReservationEntity
import com.example.domain.repo.UserRepo


class userRepoImpl(
    private val myDatabase: MyDatabase
) : UserRepo {
    private val TAG: String? = "userRepoImpl"
    override suspend fun insertDeviceToDatabase(
        device: DeviceEntity,
        result: (Resource<String>) -> Unit
    ) {
        try {
            myDatabase.DeviceEntityDao().insertStationItemEntityDao(device)
            result.invoke(Resource.Success("Successfully added Device data item in database"))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed inserting DeviceItemToDatabase ---> ${e.message}"))
        }
    }

    override suspend fun getAllPlaystationReservations(result: (Resource<ArrayList<PlaystationReservationEntity>>) -> Unit) {
        try {
            result.invoke(
                Resource.Success(
                    ArrayList(
                        myDatabase.PlaystationReservationEntityDao().getPlaystationReservationEntities()
                    )
                )
            )
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed  getting Playstation Reservation Entities ---> ${e.message}"))
        }
    }

    override suspend fun updatePlaystationReservation(
        playstationReservationEntity: PlaystationReservationEntity,
        result: (Resource<String>) -> Unit
    ) {
        try {
            myDatabase.PlaystationReservationEntityDao().update(playstationReservationEntity)
            result.invoke(Resource.Success("Successfully Updated Playstation reservation device"))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed Updated Playstation reservation---> ${e.message}"))
        }
    }

    override suspend fun getDevicesFromDatabase(result: (Resource<ArrayList<DeviceEntity>>) -> Unit) {
        try {
            result.invoke(
                Resource.Success(
                    ArrayList(
                        myDatabase.DeviceEntityDao().getDevices()
                    )
                )
            )
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed getting StationAlarmsFromDatabase ---> ${e.message}"))
        }
    }

    override suspend fun deleteDeviceFromDatabaseByID(
        id: Long?,
        result: (Resource<String>) -> Unit
    ) {
        try {
            myDatabase.DeviceEntityDao().deleteDevice(id)
            result.invoke(Resource.Success("Successfully deleted device"))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed deleting device ---> ${e.message}"))
        }
    }

    override suspend fun getLastDeviceNumber(result: (Resource<Int>) -> Unit) {
        try {
            result.invoke(
                Resource.Success(
                        myDatabase.DeviceEntityDao().getLastValue()
                )
            )
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed getting StationAlarmsFromDatabase ---> ${e.message}"))
        }
    }

    override suspend fun insertNewPlaystationReservation(playstationReservationEntity: PlaystationReservationEntity, result: (Resource<String>) -> Unit) {
        try {
            myDatabase.PlaystationReservationEntityDao().insertPlaystationReservationEntity(playstationReservationEntity)
            result.invoke(Resource.Success("Successfully added playstationReservationEntity data item in database"))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed inserting playstationReservationEntity ---> ${e.message}"))
        }    }

}