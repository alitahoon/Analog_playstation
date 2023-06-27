package com.example.shoshaplaystation.di

import com.example.domain.repo.UserRepo
import com.example.domain.usercases.GetDevicesFromDatabase
import com.example.domain.usercases.InsertDeviceToDatabase
import dagger.Provides

object usecaseModule {

    @Provides
    fun provideGetDevicesFromDatabase(userRepo: UserRepo):GetDevicesFromDatabase{
        return GetDevicesFromDatabase(userRepo)
    }

    @Provides
    fun provideInsertDeviceToDatabase(userRepo: UserRepo):InsertDeviceToDatabase{
        return InsertDeviceToDatabase(userRepo)
    }
}