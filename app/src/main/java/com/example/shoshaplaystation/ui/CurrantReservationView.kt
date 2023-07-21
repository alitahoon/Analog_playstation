package com.example.shoshaplaystation.ui

import Resource
import com.example.domain.entity.PlaystationReservationEntity

interface CurrantReservationView {
    fun onGettingReservations(result: Resource<ArrayList<PlaystationReservationEntity>>)
}