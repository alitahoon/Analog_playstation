package com.example.trainlivelocation.utli

import com.example.domain.entity.DeviceEntity


interface DeviceListener {
 fun onDeviceClicked(device: DeviceEntity)
 fun onDeviceLongClicked(device: DeviceEntity):Boolean
}