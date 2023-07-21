package com.example.shoshaplaystation.util

import com.example.domain.entity.PlaystationReservationEntity

interface PlaystationReservationListener {
    fun onDeviceClicked(reservation: PlaystationReservationEntity)
    fun onDeviceLongClicked(reservation: PlaystationReservationEntity):Boolean
}