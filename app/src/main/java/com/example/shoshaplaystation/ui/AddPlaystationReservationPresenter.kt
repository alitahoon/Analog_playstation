package com.example.shoshaplaystation.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import javax.inject.Inject

class AddPlaystationReservationPresenter @Inject constructor(

) {
    private var view: AddPlaystationReservationView? = null
    private val coroutineScope: CoroutineScope = MainScope()
    private val TAG = "AddPlaystationReservationView"
    fun attachView(view: AddPlaystationReservationView) {
        this.view = view
    }
}