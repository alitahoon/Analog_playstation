package com.example.data.dataSources

import androidx.room.*
import com.example.domain.entity.PlaystationReservationEntity
@Dao
interface PlaystationReservationEntityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlaystationReservationEntity(playstationReservationEntity: PlaystationReservationEntity)

    @Query("SELECT * FROM PlaystationReservationEntity")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    fun getPlaystationReservationEntities(): List<PlaystationReservationEntity>

}