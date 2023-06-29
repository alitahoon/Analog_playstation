package com.example.shoshaplaystation.ui

import Resource
import com.example.domain.entity.Device
import com.example.domain.entity.DeviceEntity
import com.example.domain.usercases.GetDevicesFromDatabase
import com.example.domain.usercases.InsertDeviceToDatabase
import kotlinx.coroutines.*
import javax.inject.Inject

class AddDevicePresenter @Inject constructor(
    private val insertDeviceToDatabase: InsertDeviceToDatabase,
    private val getDevicesFromDatabase: GetDevicesFromDatabase
) {
    private var view: AddDeviceView? = null
    private val coroutineScope: CoroutineScope = MainScope()
    private val TAG = "AddDevicePresenter"

    fun attachView(view: AddDeviceView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
        coroutineScope.cancel() // Cancel the coroutine scope
    }

    fun insertNewDeviceToDatabase(device: DeviceEntity) {

        coroutineScope.launch {
            val child1=coroutineScope.launch (Dispatchers.IO) {
                insertDeviceToDatabase(device){
                    val child=launch (Dispatchers.Main){
                        view!!.addDeviceToDatabase(it)
                    }
                }
            }
            child1.join()

        }
    }

}