package com.example.shoshaplaystation.ui

import Resource

interface DeleteDeviceDialogView {

    fun onDeviceDeleted(result: Resource<String>)
}