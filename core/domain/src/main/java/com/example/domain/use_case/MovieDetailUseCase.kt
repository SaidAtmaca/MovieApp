package com.example.domain.use_case

import com.example.common.utils.Resource
import com.example.domain.repository.AppRepository
import com.example.model.MovieDetailModel
import kotlinx.coroutines.flow.Flow

class MovieDetailUseCase(
    private val repository: AppRepository
) {

    fun getMovieDetails(movieId:Int) : Flow<Resource<MovieDetailModel>> {
        return repository.getMovieDetails(movieId)
    }
}