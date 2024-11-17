package com.example.movieapp.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.BuildConfig
import com.example.movieapp.core.common.Constants
import com.example.movieapp.core.common.Constants.TIME_OUT_RETROFIT
import com.example.movieapp.data.local.AppDatabase
import com.example.movieapp.data.remote.APIService
import com.example.movieapp.data.repository.AppRepositoryImpl
import com.example.movieapp.domain.repository.AppRepository
import com.example.movieapp.domain.use_case.MovieDetailUseCase
import com.example.movieapp.domain.use_case.NowPlayingUseCase
import com.example.movieapp.domain.use_case.PopularMoviesUseCase
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
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }


    @Provides
    fun provideOkHttpClient(): OkHttpClient {

        val builder = OkHttpClient.Builder()
            .readTimeout(TIME_OUT_RETROFIT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_RETROFIT, TimeUnit.SECONDS)

        // Header eklemek için bir interceptor
        builder.addInterceptor { chain ->
            // Token'ı dinamik olarak almak için örnek: SharedPreferences'tan

            val newRequest = chain.request().newBuilder()
                .addHeader("Accept", "application/json") // Başlıkları ekle
                .addHeader("Authorization", "Bearer ${Constants.API_TOKEN}") // Dinamik token ekle
                .build()

            // İstek ile devam et
            chain.proceed(newRequest)
        }

        // HTTP loglarını gösterme (geliştirme aşamasında yararlı)
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })

        return builder.build()
    }

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
            app, AppDatabase::class.java, Constants.ROOM_DB_NAME
        ).build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }


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
    fun provideUserLiveUseCase(repository: AppRepository):UserLiveUseCase{
        return UserLiveUseCase(repository)
    }
}