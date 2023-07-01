package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dataSources.DeviceEntityDao
import com.example.data.dataSources.PlaystationReservationEntityDao
import com.example.domain.entity.*

@Database(
    entities = [DeviceEntity::class,PlaystationReservationEntity::class],
    version = 2
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun DeviceEntityDao(): DeviceEntityDao
    abstract fun PlaystationReservationEntityDao(): PlaystationReservationEntityDao


    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "MY_DATABASE"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
