package com.example.movieapp.di

import com.example.common.Constants.API_TOKEN
import com.example.common.Constants.BASE_URL
import com.example.common.Constants.TIME_OUT_RETROFIT
import com.example.network.remote.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
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
                .addHeader("Authorization", "Bearer ${API_TOKEN}") // Dinamik token ekle
                .build()

            // İstek ile devam et
            chain.proceed(newRequest)
        }

        // HTTP loglarını gösterme (geliştirme aşamasında yararlı)
        builder.addInterceptor(HttpLoggingInterceptor().apply {
            level =  HttpLoggingInterceptor.Level.BODY
        })

        return builder.build()
    }



    @Provides
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

}