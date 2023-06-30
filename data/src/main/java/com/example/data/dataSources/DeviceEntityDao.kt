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


    @Query("DELETE FROM DeviceEntity WHERE id = :id")
    fun deleteDevice(id:Long?)

    @Query("SELECT deviceNumber FROM DeviceEntity ORDER BY deviceNumber DESC LIMIT 1")
    fun getLastValue(): Int
}