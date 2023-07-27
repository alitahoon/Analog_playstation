package com.example.shoshaplaystation.services

import Resource
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.domain.entity.PlaystationReservationEntity
import com.example.domain.usecases.GetAllPlaystationReservations
import com.example.shoshaplaystation.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PlaystationReservationService : Service() {
    private var timerCount:Long = 0
    @Inject
    lateinit var getAllPlaystationReservations: GetAllPlaystationReservations

    private val coroutineScope: CoroutineScope = MainScope()
    private var notification:Notification?=null

    private val TAG="ReservationService"
    private val updateInterval = 1000 // 1 second
    private var elapsedTime = 0L
    private var notificationManager: NotificationManager? = null
    private var notificationLayout:RemoteViews?=null
    companion object{
        private val NOTIFICATION_CHANNEL=101
        private val NOTIFICATION_NAME="PLASTATION_RESERVATION"
        private var NOTIFICATION_ID=100

        // Service Actions
        const val START = "START"
        const val PAUSE = "PAUSE"
        const val RESET = "RESET"
        const val GET_STATUS = "GET_STATUS"
        const val MOVE_TO_FOREGROUND = "MOVE_TO_FOREGROUND"
        const val MOVE_TO_BACKGROUND = "MOVE_TO_BACKGROUND"

        // Intent Extras
        const val NOTIFICATION_ACTION = "STOPWATCH_ACTION"
        const val TIME_ELAPSED = "TIME_ELAPSED"
        const val IS_NOTIFICATION_RUNNING = "IS_STOPWATCH_RUNNING"

        // Intent Actions
        const val NOTIFICATIONTICK = "STOPWATCH_TICK"
        const val NOTIFICATION_STATUS = "STOPWATCH_STATUS"
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CreateNotificationChannel()
        val action = intent?.getStringExtra(NOTIFICATION_ACTION)!!
        when (action) {
            START -> startNOTIFICATION()
            PAUSE -> pauseNOTIFICATION()
            RESET -> resetNOTIFICATION()
            GET_STATUS -> sendStatus()
            MOVE_TO_FOREGROUND -> moveToForeground()
            MOVE_TO_BACKGROUND -> moveToBackground()
        }
    return START_STICKY
    }

    private fun moveToBackground() {
        TODO("Not yet implemented")
    }

    private fun moveToForeground() {
        TODO("Not yet implemented")
    }

    private fun sendStatus() {
        TODO("Not yet implemented")
    }

    private fun resetNOTIFICATION() {
        TODO("Not yet implemented")
    }

    private fun pauseNOTIFICATION() {
        TODO("Not yet implemented")
    }

    private fun startNOTIFICATION() {
        coroutineScope.launch (Dispatchers.IO){
            getAllPlaystationReservations(){
                when(it){
                    is Resource.Loading->{
                        Log.i(TAG,"getting alarms ....")
                    }
                    is Resource.Success->{
                        Log.i(TAG,"${it.data}")
                        for (reservation in it.data){
                            setNotificationData(reservation)
                        }
                    }
                    is Resource.Failure->{
                        Log.e(TAG,"${it.error}")
                    }
                    else -> {}
                }
            }
        }
    }

    fun CreateNotificationChannel(){
        // Create the notification channel (call this once, preferably in Application class or MainActivity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                (NOTIFICATION_CHANNEL+1).toString(),
                NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager!!.createNotificationChannel(channel)
        }
    }

    fun setNotificationData(playstationReservationEntity: PlaystationReservationEntity){
        notificationLayout = RemoteViews(packageName, R.layout.playstation_reservation_notification_layout)
        notificationLayout!!.setTextViewText(R.id.playstationReservationtxtDevice, "Device : ${playstationReservationEntity.deviceId}")
        notificationLayout!!.setTextViewText(R.id.playstationReservationtxtPrice,"Price : ${playstationReservationEntity.price} EGP")
        // Schedule timer updates using Handler
        coroutineScope.launch (Dispatchers.Main
        ){
            startNotificationTimer((playstationReservationEntity.remainingTime*60).toLong(),playstationReservationEntity)
        }


    }
    private fun startNotificationTimer(durationInSeconds: Long,playstationReservationEntity: PlaystationReservationEntity) {
        timerCount = durationInSeconds
        showNotification(playstationReservationEntity)

        val countDownTimer = object : CountDownTimer(durationInSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerCount = millisUntilFinished / 1000
                val hours = timerCount / 3600
                val minutes = (timerCount % 3600) / 60
                val seconds = timerCount % 60
                val remainingTimeText = String.format("Remaining Time: %02d Hrs : %02d Min : %02d Sec", hours, minutes, seconds)

                // Update the notification layout on the main thread using MainScope
                Log.i(TAG, "$remainingTimeText")
                notificationLayout!!.setTextViewText(R.id.playstationReservationtxtReaminingTime, remainingTimeText)
                notificationManager?.notify(playstationReservationEntity.id.toInt() + 1, notification)

            }

            override fun onFinish() {
                // Timer finished, perform your action here
            }
        }
        countDownTimer.start()

    }


    fun showNotification(playstationReservationEntity: PlaystationReservationEntity) {
            // Update the notification
        notification = NotificationCompat.Builder(
                this@PlaystationReservationService,
                (NOTIFICATION_CHANNEL + 1).toString()
            )
                .setColorized(true)
                .setCustomBigContentView(notificationLayout)
                .setColor(Color.parseColor("#6DA9E4"))
                .setSmallIcon(R.drawable.controller2_white)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .build()
            NOTIFICATION_ID = playstationReservationEntity.id.toInt() + 1
            notificationManager?.notify(NOTIFICATION_ID, notification)
        }

}