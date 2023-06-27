package com.example.trainlivelocation.utli

import com.example.domain.entity.Device


interface DeviceListener {
 fun onDeviceClicked(device: Device)
}