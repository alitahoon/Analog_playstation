package com.example.shoshaplaystation.di

import android.content.Context
import com.example.data.repo.userRepoImpl
import com.example.domain.repo.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.example.data.*

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun ProvideMyDatabase(context: Context): MyDatabase {
        return MyDatabase.getInstance(context)
    }


    @Provides
    fun ProvideRepo(
        myDatabase: MyDatabase
    ): UserRepo {
        return userRepoImpl(
            myDatabase
        )
    }

}