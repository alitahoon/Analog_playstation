package com.example.data.dataSources

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.domain.entity.DeviceEntity
import com.example.domain.entity.PlaystationReservationEntity

interface PlaystationReservationEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaystationReservationEntity(playstationReservationEntity: PlaystationReservationEntity)
}