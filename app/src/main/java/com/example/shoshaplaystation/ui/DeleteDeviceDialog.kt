package com.example.shoshaplaystation.ui

import Resource
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.domain.entity.DeviceEntity
import com.example.shoshaplaystation.R
import com.example.shoshaplaystation.databinding.FragmentDeleteDeviceDialogBinding
import com.example.shoshaplaystation.databinding.FragmentHomeBinding
import com.example.shoshaplaystation.util.DeviceCountChangeListener
import com.example.shoshaplaystation.util.toast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DeleteDeviceDialog(
    private val deviceCountChangeListener: DeviceCountChangeListener,
    private val device: DeviceEntity
) : DialogFragment(), DeleteDeviceDialogView {
    private var binding: FragmentDeleteDeviceDialogBinding? = null
    private val TAG = "Home"

    @Inject
    lateinit var deleteDeviceDialogPresenter: DeleteDeviceDialogPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deleteDeviceDialogPresenter.attachView(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeleteDeviceDialogBinding.inflate(inflater, container, false)
            .apply {

            }
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding!!.deleteDeviceBtnYes.setOnClickListener {
            deleteDeviceDialogPresenter.deleteDeviceFromDatabase(device)
        }
        binding!!.deleteDeviceBtnNo.setOnClickListener{
            dismiss()
        }

        return binding!!.root
    }

    companion object {

    }

    override fun onDeviceDeleted(result: Resource<String>) {
        when (result) {
            is Resource.Loading -> {
                toast("waiting ....")
            }
            is Resource.Failure -> {
                toast("failed to delete device data")
            }
            is Resource.Success -> {
                toast("Device data deleted successfully")
                deviceCountChangeListener.onDeviceDeleted()
                dismiss()
            }

            else -> {}
        }
    }
}