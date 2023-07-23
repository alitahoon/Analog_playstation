package com.example.shoshaplaystation.util

import Resource
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.domain.entity.PlaystationReservationEntity
import com.example.domain.usecases.UpdatePlaystationReservation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class bindingMethods @Inject constructor(
    private val updatePlaystationReservation: UpdatePlaystationReservation
) : onReamingTimeChanged {
    init {
        setListener(this)
    }

    private val TAG = "bindingMethods"
    val coroutineScope: CoroutineScope = MainScope()

    companion object {
        private var listener: onReamingTimeChanged? = null
        fun setListener(listener: onReamingTimeChanged) {
            this.listener = listener
        }


        @BindingAdapter("setDeviceNumber")
        @JvmStatic
        fun setDeviceNumber(textView: TextView, deviceNumber: Int) {
            textView.setText("Device ${deviceNumber}")

        }

        @BindingAdapter("remainingTime", "reservationModel")
        @JvmStatic
        fun setRemainingTime(
            textView: TextView,
            remainingTime: Double,
            reservationModel: PlaystationReservationEntity
        ) {
            val remainingSeconds = (remainingTime * 60).toLong()
            val timer = object : CountDownTimer(remainingSeconds * 1000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val seconds = millisUntilFinished / 1000
                    val minutes = seconds / 60
                    val secondsRemainder = seconds % 60
                    textView.text =
                        "Remaining Time: $minutes:${String.format("%02d", secondsRemainder)}"
                    listener!!.setReamingTime(
                        PlaystationReservationEntity(
                            id = reservationModel.id,
                            deviceId = reservationModel.deviceId,
                            startTime = reservationModel.startTime,
                            price = reservationModel.price,
                            CurrantDate = reservationModel.CurrantDate,
                            remainingTime = minutes.toDouble(),
                            reservationType = reservationModel.reservationType
                        )
                    )

                }

                override fun onFinish() {
                    textView.text = "Time's up!"
                    // Handle countdown timer completion here
                }
            }

            timer.start()
        }

        @BindingAdapter("setPrice")
        @JvmStatic
        fun setPrice(textView: TextView, price: Double) {
            textView.setText("Price : ${price} EGP")

        }

        @BindingAdapter("setType")
        @JvmStatic
        fun setType(textView: TextView, type: String) {
            textView.setText("Type : ${type}")

        }
    }

    override fun setReamingTime(playstationReservationEntity: PlaystationReservationEntity) {
        coroutineScope.launch(Dispatchers.IO) {
            updatePlaystationReservation.invoke(playstationReservationEntity) {
                when (it) {
                    is Resource.Success -> {
                        Log.i(TAG, "${it.data}")
                    }
                    is Resource.Loading -> {
                        Log.i(TAG, "waiting for updating alarm..")
                    }
                    is Resource.Failure -> {
                        Log.e(TAG, "${it.error}")
                    }
                    else -> {}
                }
            }
        }
    }

}

interface onReamingTimeChanged {
    fun setReamingTime(playstationReservationEntity: PlaystationReservationEntity)
}