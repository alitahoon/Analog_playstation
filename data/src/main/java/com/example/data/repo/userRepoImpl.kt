package com.example.data.repo

import Resource
import com.example.data.dataSources.MyDatabase
import com.example.domain.entity.Device
import com.example.domain.entity.DeviceEntity
import com.example.domain.repo.UserRepo

class userRepoImpl(
    private val myDatabase: MyDatabase
) : UserRepo {
    private val TAG: String? = "userRepoImpl"
    override suspend fun insertDeviceToDatabase(
        device: Device,
        result: (Resource<String>) -> Unit
    ) {
        try {
            myDatabase.DeviceEntityDao().insertStationItemEntityDao(device)
            result.invoke(Resource.Success("Successfully added device data item in database"))
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed inserting device data item to database ---> ${e.message}"))
        }
    }

    override suspend fun getDevicesFromDatabase(result: (Resource<ArrayList<DeviceEntity>>) -> Unit) {
        try {
            result.invoke(
                Resource.Success(
                    ArrayList(
                        myDatabase.DeviceEntityDao().getStationItemEntityDao()
                    )
                )
            )
        } catch (e: Exception) {
            result.invoke(Resource.Failure("Failed getting device items from database ---> ${e.message}"))
        }    }

}