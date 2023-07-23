package com.example.shoshaplaystation.di

import com.example.domain.repo.UserRepo
import com.example.domain.usecases.*
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

    @Provides
    fun provideGetLastDeviceNumber(userRepo: UserRepo):GetLastDeviceNumber{
        return GetLastDeviceNumber(userRepo)
    }

    @Provides
    fun provideInsertNewPlaystationReservation(userRepo: UserRepo):InsertNewPlaystationReservation{
        return InsertNewPlaystationReservation(userRepo)
    }
    @Provides
    fun provideGetAllPlaystationReservations(userRepo: UserRepo):GetAllPlaystationReservations{
        return GetAllPlaystationReservations(userRepo)
    }

    @Provides
    fun provideUpdatePlaystationReservation(userRepo: UserRepo):UpdatePlaystationReservation{
        return UpdatePlaystationReservation(userRepo)
    }

}