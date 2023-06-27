package com.example.shoshaplaystation.di

import com.example.data.dataSources.MyDatabase
import com.example.data.repo.userRepoImpl
import com.example.domain.repo.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun ProvideRepo(
        myDatabase: MyDatabase
    ): UserRepo {
        return userRepoImpl(
            myDatabase
        )
    }

}