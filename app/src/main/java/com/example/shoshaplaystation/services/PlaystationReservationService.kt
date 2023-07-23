package com.example.shoshaplaystation.services

import android.app.Service
import android.content.Intent
import android.os.IBinder

class PlaystationReservationService : Service() {
    companion object{
        // Channel ID for notifications
        const val CHANNEL_ID = "PlaystationReservationService_Notifications"

        // Service Actions
        const val START = "START"
        const val PAUSE = "PAUSE"
        const val RESET = "RESET"
        const val GET_STATUS = "GET_STATUS"
        const val MOVE_TO_FOREGROUND = "MOVE_TO_FOREGROUND"
        const val MOVE_TO_BACKGROUND = "MOVE_TO_BACKGROUND"

        // Intent Extras
        const val STOPWATCH_ACTION = "PlaystationReservationService_ACTION"
        const val TIME_ELAPSED = "TIME_ELAPSED"
        const val IS_STOPWATCH_RUNNING = "IS_PlaystationReservationService_RUNNING"

        // Intent Actions
        const val STOPWATCH_TICK = "PlaystationReservationService_TICK"
        const val STOPWATCH_STATUS = "PlaystationReservationService_STATUS"
    }
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}