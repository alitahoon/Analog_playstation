package com.example.data.dataSources

import androidx.room.*
import com.example.domain.entity.StartedReservationsServicesEntity
@Dao
interface StartedReservationsServicesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(startedReservationsServicesEntity: StartedReservationsServicesEntity)

    @Query("SELECT * FROM StartedReservationsServicesEntity")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    fun getAll(): List<StartedReservationsServicesEntity>


    @Update
    fun update(startedReservationsServicesEntity: StartedReservationsServicesEntity)

    @Query("DELETE FROM StartedReservationsServicesEntity")
    fun clear()

    @Query("DELETE FROM StartedReservationsServicesEntity WHERE id = :id")
    fun deleteItemWithID(id:Long)
}