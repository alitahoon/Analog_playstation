package com.example.data.repo

import Resource
import com.example.data.MyDatabase
import com.example.domain.entity.DeviceEntity
import com.example.domain.entity.PlaystationReservationEntity
import com.example.domain.entity.StartedReservationsServicesEntity
import com.example.domain.entity.WatiedReservationsServicesEntity
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

    override suspend fun insertNewStartedReservationItem(
        startedReservationsServicesEntity: StartedReservationsServicesEntity,
        result: (Resource<String>) -> Unit
    ) {
        try {
            myDatabase.StartedReservationsServicesDao().insert(startedReservationsServicesEntity)
            result.invoke(Resource.Success("Successfully added in database"))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed inserting.. ---> ${e.message}"))
        }    }

    override suspend fun insertNewWaitedReservationItem(
        watiedReservationsServicesEntity: WatiedReservationsServicesEntity,
        result: (Resource<String>) -> Unit
    ) {
        try {
            myDatabase.WatiedReservationsServicesDao().insert(watiedReservationsServicesEntity)
            result.invoke(Resource.Success("Successfully added in database"))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed inserting.. ---> ${e.message}"))
        }
    }

    override suspend fun getAllStartedPlaystationReservations(result: (Resource<ArrayList<StartedReservationsServicesEntity>>) -> Unit) {
        try {
            result.invoke(
                Resource.Success(
                    ArrayList(
                        myDatabase.StartedReservationsServicesDao().getAll()
                    )
                )
            )
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed  getting Playstation Reservation Entities ---> ${e.message}"))
        }    }

    override suspend fun getAllWaitedPlaystationReservations(result: (Resource<ArrayList<WatiedReservationsServicesEntity>>) -> Unit) {
        try {
            result.invoke(
                Resource.Success(
                    ArrayList(
                        myDatabase.WatiedReservationsServicesDao().getAll()
                    )
                )
            )
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed  getting Playstation Reservation Entities ---> ${e.message}"))
        }     }

    override suspend fun deleteItemFromStartedReservationWithId(
        id: Long?,
        result: (Resource<String>) -> Unit
    ) {
        try {
            myDatabase.StartedReservationsServicesDao().deleteItemWithID(id!!)
            result.invoke(Resource.Success("Successfully deleted "))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed deleting  ---> ${e.message}"))
        }      }

    override suspend fun deleteItemFromPlaystationReservationWithId(
        id: Long?,
        result: (Resource<String>) -> Unit
    ) {
        try {
            myDatabase.PlaystationReservationEntityDao().deleteItemWithID(id!!)
            result.invoke(Resource.Success("Successfully deleted "))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed deleting  ---> ${e.message}"))
        }       }

    override suspend fun deleteItemFromWaitedReservationWithId(
        id: Long?,
        result: (Resource<String>) -> Unit
    ) {
        try {
            myDatabase.WatiedReservationsServicesDao().deleteItemWithID(id!!)
            result.invoke(Resource.Success("Successfully deleted "))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed deleting  ---> ${e.message}"))
        }    }

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

    override suspend fun clearPlaystationReservation(result: (Resource<String>) -> Unit) {
        try {
            myDatabase.PlaystationReservationEntityDao().clear()
            result.invoke(Resource.Success("Successfully  clear Playstation reservations device"))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed Updated clear reservations---> ${e.message}"))
        }    }

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