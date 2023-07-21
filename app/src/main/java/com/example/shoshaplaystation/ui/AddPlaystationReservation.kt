package com.example.shoshaplaystation.ui

import Resource
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.domain.entity.Device
import com.example.domain.entity.PlaystationReservationEntity
import com.example.shoshaplaystation.databinding.FragmentAddPlaystationReservationBinding
import com.example.shoshaplaystation.util.CustomTimePickerDialogListener
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class AddPlaystationReservation : Fragment(), AddPlaystationReservationView,
    CustomTimePickerDialogListener {

    private val TAG = "AddReservation"
    private var binding: FragmentAddPlaystationReservationBinding? = null
    private var device: Device? = null

    @Inject
    lateinit var addPlaystationReservationPresenter: AddPlaystationReservationPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        device = requireArguments().getParcelable("device")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPlaystationReservationBinding.inflate(inflater, container, false)
            .apply {

            }
        addPlaystationReservationPresenter.attachView(this)

        binding!!.addPlaystationReservationBtnChooseStartTime.setOnClickListener {
            val bottomSheetDialogFragment = CustomTimePickerDialog(this)
            bottomSheetDialogFragment.show(childFragmentManager, "CustomTimePickerDialog")
        }

        binding!!.addPlaystationReservationtxtHours.setOnValueChangedListener{ _, _, newVal ->
            // Do something with the selected value (newVal)
            // For example, show it in a toast message:
            reservationHours=newVal.toDouble()
            Toast.makeText(requireContext(), "Selected Hours: $newVal", Toast.LENGTH_SHORT).show()
        }
        binding!!.addPlaystationReservationtxtMinutes.setOnValueChangedListener{ _, _, newVal ->
            // Do something with the selected value (newVal)
            // For example, show it in a toast message:
            reservationMinutes=newVal.toDouble()
            Toast.makeText(requireContext(), "Selected Minutes: $newVal", Toast.LENGTH_SHORT).show()
        }

        binding!!.addPlaystationReservationTyperadioGroup.setOnCheckedChangeListener { _, checkedId ->
            // Get the selected RadioButton
            val selectedRadioButton: RadioButton = requireActivity().findViewById(checkedId)

            // Get the text of the selected RadioButton
            val selectedText = selectedRadioButton.text.toString()
            reservationType=selectedText
            when(selectedText){
                "Single"->{
                    var minutesPrice=(reservationMinutes/60)*15
                    reservationPrice=
                        reservationHours?.times(15)?.plus(minutesPrice)!!.toDouble()
                    Log.i(TAG,"price $reservationPrice")
                }
                "Multi"->{
                    var minutesPrice=(reservationMinutes/60)*25
                    reservationPrice=
                        reservationHours?.times(25)?.plus(minutesPrice)!!.toDouble()
                    Log.i(TAG,"price $reservationPrice")
                }
            }

            // Do something with the selected text (e.g., show a toast)
            Toast.makeText(requireContext(), "Selected option: $selectedText", Toast.LENGTH_SHORT).show()
        }

        binding!!.addPlaystationReservationBtnStart.setOnClickListener {
            addPlaystationReservationPresenter.addPlaystationReservation(
                PlaystationReservationEntity(
                    deviceId = device!!.id!!,
                    CurrantDate = LocalDate.now().toString(),
                    startTime = binding!!.addPlaystationReservationBtnChooseStartTime.text.toString(),
                    reservationType = reservationType!!,
                    price = reservationPrice!!
                )
            )
        }
        return binding!!.root
    }

    companion object {
        private var reservationPrice:Double=0.0
        private var reservationType:String?=null
        private var reservationHours:Double=0.0
        private var reservationMinutes:Double=0.0
    }


    override fun onTimeSelected(hourOfDay: Int, minute: Int, NotSelectedTime: String?) {
        if (hourOfDay == 0 && minute == 0) {
            binding!!.addPlaystationReservationBtnChooseStartTime.setText("No Time Selected")
        } else if (hourOfDay >= 12) {
            binding!!.addPlaystationReservationBtnChooseStartTime.setText("${hourOfDay - 12} : ${minute} AM")
        } else {
            binding!!.addPlaystationReservationBtnChooseStartTime.setText("${hourOfDay}: ${minute} PM")
        }
    }

    override fun onAddPlaystationReservation(result: Resource<String>) {
        when (result) {
            is Resource.Success -> {
                Log.i(TAG, "${result.data}")
            }
            is Resource.Failure -> {
                Log.e(TAG, "${result.error}")
            }
            is Resource.Loading -> {
                Log.i(TAG, "Adding reservation please wait....")
            }
            else -> {}
        }
    }


}