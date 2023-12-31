package com.example.shoshaplaystation.services

import Resource
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.domain.entity.PlaystationReservationEntity
import com.example.domain.usecases.DeleteItemFromPlaystationReservationWithId
import com.example.domain.usecases.GetAllPlaystationReservations
import com.example.domain.usecases.InsertNewPlaystationReservation
import com.example.shoshaplaystation.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PlaystationReservationService_d1 : Service() {
    private var timerCount:Long = 0
    @Inject
    lateinit var getAllPlaystationReservations: GetAllPlaystationReservations

    @Inject
    lateinit var deleteItemFromPlaystationReservationWithId: DeleteItemFromPlaystationReservationWithId

    @Inject
    lateinit var insertNewPlaystationReservation: InsertNewPlaystationReservation


    private val coroutineScope: CoroutineScope = MainScope()

    private val TAG="ReservationService"
    private val updateInterval = 1000 // 1 second
    private var elapsedTime = 0L
    private var notificationManager: NotificationManager? = null
    private var notificationLayout:RemoteViews?=null
    companion object{
        private val NOTIFICATION_CHANNEL=101
        private val NOTIFICATION_NAME="PLASTATION_RESERVATION1"
        private var NOTIFICATION_ID=106

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
        const val DEVICE_DATA = "DEVICE_DATA"
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        CreateNotificationChannel()
//        val action = intent?.getStringExtra(NOTIFICATION_ACTION)!!
        val devicesReservationItem:PlaystationReservationEntity=intent?.getParcelableExtra(DEVICE_DATA)!!
        setNotificationData(devicesReservationItem)
        Log.i(TAG,"item ${devicesReservationItem}")


//        when (action) {
//            START -> startNOTIFICATION()
//            PAUSE -> pauseNOTIFICATION()
//            RESET -> resetNOTIFICATION()
//            GET_STATUS -> sendStatus()
//            MOVE_TO_FOREGROUND -> moveToForeground()
//            MOVE_TO_BACKGROUND -> moveToBackground()
//
//        }
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
                            updateItemStatusInDatabase(reservation)
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
                (NOTIFICATION_CHANNEL).toString(),
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
       val notification= showNotification(playstationReservationEntity)

        val countDownTimer = object : CountDownTimer(durationInSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerCount = millisUntilFinished / 1000
                val hours = timerCount / 3600
                val minutes = (timerCount % 3600) / 60
                val seconds = timerCount % 60
                val remainingTimeText = String.format("Remaining Time: \n %02d Hrs : %02d Min : %02d Sec", hours, minutes, seconds)

                // Update the notification layout on the main thread using MainScope
                Log.i(TAG, "$remainingTimeText")
                notificationLayout!!.setTextViewText(R.id.playstationReservationtxtReaminingTime, remainingTimeText)
                notificationManager?.notify(NOTIFICATION_ID, notification)

            }

            override fun onFinish() {
                // Timer finished, perform your action here
            }
        }
        countDownTimer.start()

    }

    fun updateItemStatusInDatabase(playstationReservationEntity: PlaystationReservationEntity) {
        //first delete item from the Old table
        coroutineScope.launch(Dispatchers.IO) {
            deleteItemFromPlaystationReservationWithId(playstationReservationEntity.id){
                when(it){
                    is Resource.Success->{
                        Log.d(TAG,"${it.data}")
                        Log.i(TAG,"ggggggggggg")
                        coroutineScope.launch (Dispatchers.IO){
                            insertNewPlaystationReservation(
                                PlaystationReservationEntity(
                                deviceId = playstationReservationEntity.deviceId,
                                CurrantDate = playstationReservationEntity.CurrantDate,
                                startTime = playstationReservationEntity.startTime,
                                price = playstationReservationEntity.price,
                                reservationType = playstationReservationEntity.reservationType,
                                remainingTime = playstationReservationEntity.remainingTime
                            )
                            ){
                                when(it){
                                    is Resource.Success->{
                                        Log.d(TAG,"${it.data}")
                                        setNotificationData(playstationReservationEntity)
                                    }
                                    is Resource.Failure->{
                                        Log.e(TAG,"${it.error}")
                                    }
                                    is Resource.Loading->{
                                        Log.d(TAG,"deleting item....")
                                    }
                                    else -> {}
                                }
                            }
                        }
                    }
                    is Resource.Failure->{
                        Log.e(TAG,"${it.error}")
                    }
                    is Resource.Loading->{
                        Log.d(TAG,"deleting item....")
                    }
                    else -> {}
                }
            }
        }
    }


    fun showNotification(playstationReservationEntity: PlaystationReservationEntity): Notification {
        // Update the notification
        val notification = NotificationCompat.Builder(
            this@PlaystationReservationService_d1,
            (NOTIFICATION_CHANNEL).toString()
        )
            .setColorized(true)
            .setCustomBigContentView(notificationLayout)
            .setSmallIcon(R.drawable.controller2_white)
            .setOnlyAlertOnce(true)
            .setAutoCancel(true)
            .build()

        notificationManager?.notify(NOTIFICATION_ID, notification)
        return notification
    }

}