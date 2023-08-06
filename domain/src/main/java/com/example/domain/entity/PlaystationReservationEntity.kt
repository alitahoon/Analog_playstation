package com.example.domain.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "PlaystationReservationEntity",
    foreignKeys = [
        ForeignKey(
            entity = DeviceEntity::class,
            parentColumns = ["id"],
            childColumns = ["deviceId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@Parcelize
class PlaystationReservationEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "deviceId") val deviceId: Int,
    @ColumnInfo(name = "currantDate") val CurrantDate: String,
    @ColumnInfo(name = "startTime") val startTime: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "reservationType") val reservationType: String,
    @ColumnInfo(name = "remainingTime") val remainingTime: Double,

    ) : Parcelable