package com.example.data.dataSources

import androidx.room.*
import com.example.domain.entity.WatiedReservationsServicesEntity

interface WatiedReservationsServicesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(watiedReservationsServicesEntity: WatiedReservationsServicesEntity)

    @Query("SELECT * FROM WatiedReservationsServicesEntity")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    fun getAll(): List<WatiedReservationsServicesEntity>


    @Update
    fun update(watiedReservationsServicesEntity: WatiedReservationsServicesEntity)

    @Query("DELETE FROM WatiedReservationsServicesEntity")
    fun clear()


    @Query("DELETE FROM WatiedReservationsServicesEntity WHERE id = :id")
    fun deleteItemWithID(id:Long)
}