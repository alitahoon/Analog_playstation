package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.domain.entity.Device
import com.example.domain.entity.DeviceEntity

@Dao
interface DeviceEntityDao {

    @Query("SELECT * FROM StationItemEntity")
    suspend fun getStationItemEntityDao() : List<DeviceEntity>

    @Insert
    suspend fun insertStationItemEntityDao(device: Device)
}