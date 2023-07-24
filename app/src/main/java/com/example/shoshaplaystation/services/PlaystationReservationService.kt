package com.example.shoshaplaystation.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.domain.entity.PlaystationReservationEntity
import com.example.shoshaplaystation.R
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class PlaystationReservationService : Service() {
    @Inject


    private val TAG="ReservationService"
    private val timerHandler = Handler(Looper.getMainLooper())
    private val updateInterval = 1000 // 1 second
    private var elapsedTime = 0L
    private var notificationManager: NotificationManager? = null
    private var notificationLayout:RemoteViews?=null
    companion object{
        private val NOTIFICATION_CHANNEL=101
        private val NOTIFICATION_NAME="PLASTATION_RESERVATION"
        private val NOTIFICATION_ID=100

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
        timerHandler.post(object : Runnable {
            override fun run() {
                // Update the elapsed time in the notification
                elapsedTime += updateInterval.toLong()

                val hours = TimeUnit.MILLISECONDS.toHours(elapsedTime)
                val minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime - TimeUnit.HOURS.toMillis(hours))
                val seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime - TimeUnit.HOURS.toMillis(hours) - TimeUnit.MINUTES.toMillis(minutes))

                notificationLayout!!.setTextViewText(
                    R.id.playstationReservationtxtReaminingTime,
                    String.format("Remaining Time : %02d:%02d:%02d", hours, minutes, seconds)
                )
                showNotification()
                // Schedule the next update
                timerHandler.postDelayed(this, updateInterval.toLong())
            }
        })
    }

    fun showNotification() {
        // Update the notification
        val notification = NotificationCompat.Builder(this@PlaystationReservationService, (NOTIFICATION_CHANNEL+1).toString())
            .setSmallIcon(R.drawable.controller2_white)
            .setCustomContentView(notificationLayout )
            .setAutoCancel(true)
            .build()

        notificationManager?.notify(NOTIFICATION_ID + 1, notification)
    }
}