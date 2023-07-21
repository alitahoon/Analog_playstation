package com.example.shoshaplaystation.ui

import Resource
import com.example.domain.entity.DeviceEntity
import com.example.domain.usecases.GetDevicesFromDatabase
import com.example.domain.usecases.GetLastDeviceNumber
import com.example.domain.usecases.InsertDeviceToDatabase
import kotlinx.coroutines.*
import javax.inject.Inject

class AddDevicePresenter @Inject constructor(
    private val insertDeviceToDatabase: InsertDeviceToDatabase,
    private val getDevicesFromDatabase: GetDevicesFromDatabase,
    private val getLastDeviceNumber: GetLastDeviceNumber
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
            view!!.addDeviceToDatabase(Resource.Loading)
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

    fun getLastDeviceNumberFromDatabase() {

        coroutineScope.launch {
            view!!.getLastDeviceNumber(Resource.Loading)
            val child1=coroutineScope.launch (Dispatchers.IO) {
                getLastDeviceNumber(){
                    val child=launch (Dispatchers.Main){
                        view!!.getLastDeviceNumber(it)
                    }
                }
            }
            child1.join()

        }
    }

}