package com.example.shoshaplaystation.ui

import Resource
import com.example.domain.usecases.GetAllPlaystationReservations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject
class CurrantReservationPresenter @Inject constructor(
    private val getAllPlaystationReservations:GetAllPlaystationReservations
) {
    private val coroutineScope: CoroutineScope = MainScope()
    private var view:CurrantReservationView?=null
    private val TAG="CurrantReservationPresenter"
    fun attachView(view:CurrantReservationView){
        this.view=view
    }

    fun getReservations(){
        view!!.onGettingReservations(Resource.Loading)
        coroutineScope.launch (Dispatchers.IO){
            getAllPlaystationReservations(){
                view!!.onGettingReservations(it)
            }
        }
    }
}