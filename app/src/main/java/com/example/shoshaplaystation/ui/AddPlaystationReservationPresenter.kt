package com.example.shoshaplaystation.ui

import com.example.domain.entity.PlaystationReservationEntity
import com.example.domain.usecases.InsertNewPlaystationReservation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddPlaystationReservationPresenter @Inject constructor(
    private val insertNewPlaystationReservation: InsertNewPlaystationReservation
) {
    private var view: AddPlaystationReservationView? = null
    private val coroutineScope: CoroutineScope = MainScope()
    private val TAG = "AddPlaystationReservationView"
    fun attachView(view: AddPlaystationReservationView) {
        this.view = view
    }

    fun addPlaystationReservation(playstationReservatioEntity:PlaystationReservationEntity){
        coroutineScope.launch(Dispatchers.IO){
            insertNewPlaystationReservation(playstationReservatioEntity){
               view!!.onAddPlaystationReservation(it)
            }
        }
    }
}