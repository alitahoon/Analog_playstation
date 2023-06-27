package com.example.shoshaplaystation.ui

import com.example.domain.entity.Device

interface AddDeviceView {

    fun onAddDeviceToDatabaseClicked(device: Device)
}