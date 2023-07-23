package com.example.shoshaplaystation.util

import android.os.CountDownTimer
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.domain.entity.PlaystationReservationEntity
import com.example.shoshaplaystation.ui.CurrantReservationPresenter
import javax.inject.Inject

@Inject
lateinit var currantReservationPresenter: CurrantReservationPresenter
@BindingAdapter("setDeviceNumber")
fun setDeviceNumber(textView: TextView,deviceNumber:Int) {
  textView.setText("Device ${deviceNumber}")

}
@BindingAdapter("remainingTime","reservationModel")
fun setRemainingTime(textView: TextView,remainingTime:Double,reservationModel:PlaystationReservationEntity) {
  val remainingSeconds = (remainingTime * 60).toLong()



  val timer = object : CountDownTimer(remainingSeconds * 1000, 1000) {
    override fun onTick(millisUntilFinished: Long) {
      val seconds = millisUntilFinished / 1000
      val minutes = seconds / 60
      val secondsRemainder = seconds % 60
      textView.text = "Remaining Time: $minutes:${String.format("%02d", secondsRemainder)}"
    }

    override fun onFinish() {
      textView.text = "Time's up!"
      // Handle countdown timer completion here
    }
  }

  timer.start()
}

@BindingAdapter("setPrice")
fun setPrice(textView: TextView,price:Double) {
  textView.setText("Price : ${price}")

}

@BindingAdapter("setType")
fun setType(textView: TextView,type:String) {
  textView.setText("Type : ${type}")

}