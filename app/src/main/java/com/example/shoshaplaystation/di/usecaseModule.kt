package com.example.shoshaplaystation.di

import com.example.domain.repo.UserRepo
import com.example.domain.usercases.DeleteDeviceFromDatabaseByID
import com.example.domain.usercases.GetDevicesFromDatabase
import com.example.domain.usercases.InsertDeviceToDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object usecaseModule {

    @Provides
    fun provideGetDevicesFromDatabase(userRepo: UserRepo):GetDevicesFromDatabase{
        return GetDevicesFromDatabase(userRepo)
    }

    @Provides
    fun provideInsertDeviceToDatabase(userRepo: UserRepo):InsertDeviceToDatabase{
        return InsertDeviceToDatabase(userRepo)
    }

    @Provides
    fun provideDeleteDeviceFromDatabaseByID(userRepo: UserRepo):DeleteDeviceFromDatabaseByID{
        return DeleteDeviceFromDatabaseByID(userRepo)
    }
}