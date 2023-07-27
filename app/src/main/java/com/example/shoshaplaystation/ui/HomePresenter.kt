package com.example.shoshaplaystation.ui

import Resource
import android.util.Log
import com.example.domain.usecases.ClearPlaystationReservation
import com.example.domain.usecases.GetDevicesFromDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val getDevicesFromDatabase: GetDevicesFromDatabase,
    private val clearPlaystationReservation: ClearPlaystationReservation
) {
    private var view: HomeView? = null
    private val coroutineScope: CoroutineScope = MainScope()
    private val TAG = "HomePresenter"
    fun attachView(view: HomeView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }


    fun getDevices() {
        coroutineScope.launch() {
            val child1 = coroutineScope.launch(Dispatchers.IO) {
                getDevicesFromDatabase() {
                    val child2 = coroutineScope.launch(Dispatchers.Main) {
                        view!!.getDevicesFromDatabase(it)

                    }
                }
            }
            child1.join()

        }
    }

    fun clearReservations() {
        coroutineScope.launch() {
            val child1 = coroutineScope.launch(Dispatchers.IO) {
                clearPlaystationReservation() {
                    val child2 = coroutineScope.launch(Dispatchers.Main) {
                        when(it){
                            is Resource.Failure->{
                                Log.e(TAG,"${it.error}")
                            }
                            is Resource.Success->{
                                Log.i(TAG,"${it.data}")
                            }
                            is Resource.Loading->{
                                Log.i(TAG,"clearing devices")
                            }
                            else -> {

                            }
                        }

                    }
                }
            }
            child1.join()

        }
    }
}