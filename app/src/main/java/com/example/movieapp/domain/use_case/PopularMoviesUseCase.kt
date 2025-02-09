package com.example.movieapp.domain.use_case

import com.example.common.utils.Resource
import com.example.movieapp.data.model.PopularMoviesResponseModel
import com.example.movieapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class PopularMoviesUseCase (
    private val repository: AppRepository
) {

    fun getPopularMovies(pageValue:Int) : Flow<Resource<PopularMoviesResponseModel>> {
        return repository.getPopularMovies(pageValue)
    }
}