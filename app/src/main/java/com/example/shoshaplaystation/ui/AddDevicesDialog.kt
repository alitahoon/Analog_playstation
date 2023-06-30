package com.example.shoshaplaystation.ui

import Resource
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.domain.entity.DeviceEntity
import com.example.shoshaplaystation.databinding.FragmentAddDeviceBinding
import com.example.shoshaplaystation.util.DeviceCountChangeListener
import com.example.shoshaplaystation.util.toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddDevicesDialog (private val listener: DeviceCountChangeListener): BottomSheetDialogFragment() ,AddDeviceView{
    private val TAG="AddDevicesDialog"
    private var binding:FragmentAddDeviceBinding?=null

    @Inject
    lateinit var addDevicePresenter:AddDevicePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDevicePresenter.attachView(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddDeviceBinding.inflate(inflater,container,false)
            .apply {

            }
        addDevicePresenter.getLastDeviceNumberFromDatabase()
        binding!!.addDevicesBtnAdd.setOnClickListener{
            addDevicePresenter.insertNewDeviceToDatabase(DeviceEntity(deviceNumber = binding!!.addDeviceNumberPicker.value.toInt()))
        }
        binding!!.addDevicesBtnCancel.setOnClickListener{
            dismiss()
        }

        return binding!!.root
    }

    companion object {

    }

    override fun addDeviceToDatabase(result: Resource<String>) {
        when (result){
            is Resource.Loading->{
                toast("waiting ....")
            }
            is Resource.Failure->{
                toast("failed to add device data")
            }
            is Resource.Success->{
                listener.onDeviceAdded()
                toast("Device data added successfully")
                dismiss()
            }

            else -> {}
        }
    }

    override fun getLastDeviceNumber(result: Resource<Int>) {
        when (result){
            is Resource.Loading->{
                Log.i(TAG,"waiting ....")
            }
            is Resource.Failure->{
                Log.e(TAG,"${result.error}")
            }
            is Resource.Success->{
                Log.i(TAG,"${result.data}")
                binding!!.addDeviceNumberPicker.value=result.data
            }

            else -> {}
        }
    }
}