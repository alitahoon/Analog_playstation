package com.example.shoshaplaystation.ui

import Resource
import android.bluetooth.BluetoothClass
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
import com.example.domain.entity.PlaystationReservationEntity
import com.example.domain.entity.Device
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

        binding!!.addPlaystationReservationTyperadioGroup.setOnCheckedChangeListener { _, checkedId ->
            // Get the selected RadioButton
            val selectedRadioButton: RadioButton = requireActivity().findViewById(checkedId)

            // Get the text of the selected RadioButton
            val selectedText = selectedRadioButton.text.toString()
            when(selectedText){
                "Single"->{

                }
                "Multi"->{

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
                    reservationType =
                )
            )
        }
        return binding!!.root
    }

    companion object {

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