package com.example.shoshaplaystation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoshaplaystation.R
import com.example.shoshaplaystation.databinding.FragmentAddDeviceBinding
import com.example.shoshaplaystation.databinding.FragmentAddPlaystationReservationBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import javax.inject.Inject


class AddPlaystationReservation : Fragment() ,AddPlaystationReservationView{

    private val TAG="AddPlaystationReservation"
    private var binding: FragmentAddPlaystationReservationBinding?=null
    @Inject
    lateinit var addPlaystationReservationPresenter:AddPlaystationReservationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddPlaystationReservationBinding.inflate(inflater,container,false)
            .apply {

            }

        return binding!!.root
    }

    companion object {

    }

    override fun onAddPlaystationReservation() {

    }


}