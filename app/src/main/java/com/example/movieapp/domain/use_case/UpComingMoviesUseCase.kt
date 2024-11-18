package com.example.movieapp.domain.use_case

import com.example.movieapp.core.utils.Resource
import com.example.movieapp.data.model.UpComingMoviesResponseModel
import com.example.movieapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class UpComingMoviesUseCase(
    private val repository: AppRepository
) {

    fun getUpComingMovies(pageValue:Int) : Flow<Resource<UpComingMoviesResponseModel>> {
        return repository.getupComingMovies(pageValue)
    }
}