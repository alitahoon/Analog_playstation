package com.example.shoshaplaystation.ui

import com.example.domain.entity.DeviceEntity
import com.example.domain.usercases.DeleteDeviceFromDatabaseByID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeleteDeviceDialogPresenter @Inject constructor(
    private val deleteDeviceFromDatabaseByID: DeleteDeviceFromDatabaseByID
){
    private var view: DeleteDeviceDialogView? = null
    private val coroutineScope: CoroutineScope = MainScope()
    private val TAG = "HomePresenter"
    fun attachView(view: DeleteDeviceDialogView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun deleteDeviceFromDatabase(device: DeviceEntity) {

        coroutineScope.launch {
            val child1=coroutineScope.launch (Dispatchers.IO) {
                deleteDeviceFromDatabaseByID(device.id!!){
                    val child=launch (Dispatchers.Main){
                        view!!.onDeviceDeleted(it)
                    }
                }
            }
            child1.join()

        }
    }
}