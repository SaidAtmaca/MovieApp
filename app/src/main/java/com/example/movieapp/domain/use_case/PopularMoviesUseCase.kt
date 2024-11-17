package com.example.movieapp.domain.use_case

import com.example.movieapp.core.utils.Resource
import com.example.movieapp.data.model.NowPlayingResponseModel
import com.example.movieapp.data.model.PopularMoviesResponseModel
import com.example.movieapp.domain.repository.AppRepository
import com.example.movieapp.presentation.util.Screen
import kotlinx.coroutines.flow.Flow

class PopularMoviesUseCase (
    private val repository: AppRepository
) {

    fun getPopularMovies(pageValue:Int) : Flow<Resource<PopularMoviesResponseModel>> {
        return repository.getPopularMovies(pageValue)
    }
}