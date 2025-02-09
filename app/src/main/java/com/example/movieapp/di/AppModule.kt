package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.common.Constants.API_TOKEN
import com.example.common.Constants.BASE_URL
import com.example.common.Constants.ROOM_DB_NAME
import com.example.common.Constants.TIME_OUT_RETROFIT
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.local.AppDatabase
import com.example.movieapp.data.remote.APIService
import com.example.movieapp.data.repository.AppRepositoryImpl
import com.example.movieapp.domain.repository.AppRepository
import com.example.movieapp.domain.use_case.MovieDetailUseCase
import com.example.movieapp.domain.use_case.NowPlayingUseCase
import com.example.movieapp.domain.use_case.PopularMoviesUseCase
import com.example.movieapp.domain.use_case.UpComingMoviesUseCase
import com.example.movieapp.domain.use_case.UserLiveUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideNowPlayingUseCase(repository: AppRepository):NowPlayingUseCase{
        return NowPlayingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMovieDetailUseCase(repository: AppRepository):MovieDetailUseCase{
        return MovieDetailUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePopularMoviesUseCase(repository: AppRepository):PopularMoviesUseCase{
        return PopularMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpComingMoviesUseCase(repository: AppRepository):UpComingMoviesUseCase{
        return UpComingMoviesUseCase(repository)
    }
}