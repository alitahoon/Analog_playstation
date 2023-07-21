package com.example.shoshaplaystation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoshaplaystation.databinding.FragmentAddPlaystationReservationBinding
import com.example.shoshaplaystation.util.CustomTimePickerDialogListener
import javax.inject.Inject


class AddPlaystationReservation : Fragment() ,AddPlaystationReservationView,CustomTimePickerDialogListener{

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
        binding!!.addPlaystationReservationBtnChooseStartTime.setOnClickListener{
            val bottomSheetDialogFragment = CustomTimePickerDialog(this)
            bottomSheetDialogFragment.show(childFragmentManager, "CustomTimePickerDialog")
        }
        addPlaystationReservationPresenter.attachView(this)

        return binding!!.root
    }

    companion object {

    }

    override fun onAddPlaystationReservation() {

    }

    override fun onTimeSelected(hourOfDay: Int, minute: Int, NotSelectedTime: String?) {
        if (hourOfDay==0&&minute==0){
            binding!!.addPlaystationReservationBtnChooseStartTime.setText("No Time Selected")
        }else{
            binding!!.addPlaystationReservationBtnChooseStartTime.setText("${hourOfDay} hrs: ${minute} min")
        }
    }


}