package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.common.Constants.ROOM_DB_NAME
import com.example.data.repository.AppRepositoryImpl
import com.example.domain.repository.AppRepository
import com.example.network.remote.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        api: APIService
    ): AppRepository {
        return AppRepositoryImpl(api)
    }
}