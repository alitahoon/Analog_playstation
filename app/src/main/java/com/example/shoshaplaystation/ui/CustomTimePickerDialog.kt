package com.example.shoshaplaystation.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.shoshaplaystation.R
import com.example.shoshaplaystation.databinding.FragmentCustomTimePickerDialogBinding
import com.example.shoshaplaystation.util.CustomTimePickerDialogListener


class CustomTimePickerDialog(private val customTimePickerDialogListener: CustomTimePickerDialogListener) : DialogFragment() {
    private var binding:FragmentCustomTimePickerDialogBinding?=null
    private var hr: Int?=null
    private var min: Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCustomTimePickerDialogBinding.inflate(inflater,container,false)
            .apply {

            }

        binding!!.timePickerPicker.setOnTimeChangedListener(object :TimePicker.OnTimeChangedListener{
            override fun onTimeChanged(view: TimePicker?, hourOfDay: Int, minute: Int) {
                hr=hourOfDay
                min=minute
            }
        })
        binding!!.timePickerBtnSave.setOnClickListener{
            customTimePickerDialogListener.onTimeSelected(hr!!, min!!," ")
            dismiss()
        }
        binding!!.timePickerBtnCancel.setOnClickListener{
            customTimePickerDialogListener.onTimeSelected(0,0,"No Selected Time")
            dismiss()
        }

        dialog!!.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        return binding!!.root

    }

    companion object {

    }
}