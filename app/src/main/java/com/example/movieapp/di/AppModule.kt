package com.example.movieapp.di

import com.example.domain.repository.AppRepository
import com.example.domain.use_case.MovieDetailUseCase
import com.example.domain.use_case.NowPlayingUseCase
import com.example.domain.use_case.PopularMoviesUseCase
import com.example.domain.use_case.UpComingMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideNowPlayingUseCase(repository: AppRepository): NowPlayingUseCase {
        return NowPlayingUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMovieDetailUseCase(repository: AppRepository): MovieDetailUseCase {
        return MovieDetailUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePopularMoviesUseCase(repository: AppRepository): PopularMoviesUseCase {
        return PopularMoviesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpComingMoviesUseCase(repository: AppRepository): UpComingMoviesUseCase {
        return UpComingMoviesUseCase(repository)
    }
}