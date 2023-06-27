package com.example.shoshaplaystation.ui

import com.example.domain.entity.Device

class AddDevicePresenter {
    private var view:AddDeviceView?=null

    fun attachView(view: AddDeviceView) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun insertNewDeviceToDatabase(device:Device){

    }

}