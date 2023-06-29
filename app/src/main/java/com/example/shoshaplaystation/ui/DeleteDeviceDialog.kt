package com.example.shoshaplaystation.ui

import Resource
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.shoshaplaystation.R
import com.example.shoshaplaystation.databinding.FragmentDeleteDeviceDialogBinding
import com.example.shoshaplaystation.databinding.FragmentHomeBinding
import com.example.shoshaplaystation.util.DeviceCountChangeListener
import com.example.shoshaplaystation.util.toast
import javax.inject.Inject


class DeleteDeviceDialog(private val deviceCountChangeListener: DeviceCountChangeListener) : DialogFragment(),DeleteDeviceDialogView{
    private var binding: FragmentDeleteDeviceDialogBinding?=null
    private val TAG="Home"

    @Inject
    lateinit var  homePresenter:DeleteDeviceDialogPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDeleteDeviceDialogBinding.inflate(inflater,container,false)
            .apply {

            }

        return binding!!.root
    }

    companion object {

    }

    override fun onDeviceDeleted(result: Resource<String>) {
        when (result){
            is Resource.Loading->{
                toast("waiting ....")
            }
            is Resource.Failure->{
                toast("failed to delete device data")
            }
            is Resource.Success->{
                toast("Device data deleted successfully")
                deviceCountChangeListener.onDeviceDeleted()
                dismiss()
            }

            else -> {}
        }
    }
}