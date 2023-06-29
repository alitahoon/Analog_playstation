package com.example.data.dataSources

import androidx.room.*
import com.example.domain.entity.DeviceEntity

@Dao
interface DeviceEntityDao {

    @Query("SELECT * FROM DeviceEntity")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    fun getDevices(): List<DeviceEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStationItemEntityDao(device: DeviceEntity)


    @Query("DELETE FROM deviceentity WHERE id = :id")
    fun deleteDevice(id:Int?)
}