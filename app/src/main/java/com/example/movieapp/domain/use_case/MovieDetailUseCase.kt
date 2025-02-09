package com.example.movieapp.domain.use_case

import com.example.common.utils.Resource
import com.example.movieapp.data.model.MovieDetailModel
import com.example.movieapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class MovieDetailUseCase(
    private val repository: AppRepository
) {

    fun getMovieDetails(movieId:Int) : Flow<Resource<MovieDetailModel>> {
        return repository.getMovieDetails(movieId)
    }
}