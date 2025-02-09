package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.common.Constants.ROOM_DB_NAME
import com.example.movieapp.data.local.AppDatabase
import com.example.movieapp.data.remote.APIService
import com.example.movieapp.data.repository.AppRepositoryImpl
import com.example.movieapp.domain.repository.AppRepository
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
        db: AppDatabase,
        api: APIService
    ): AppRepository {
        return AppRepositoryImpl(api, db.roomDao)
    }


    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app, AppDatabase::class.java, ROOM_DB_NAME
        ).build()
    }
}