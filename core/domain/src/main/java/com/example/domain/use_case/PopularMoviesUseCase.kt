package com.example.domain.use_case

import com.example.common.utils.Resource
import com.example.domain.repository.AppRepository
import com.example.model.PopularMoviesResponseModel
import kotlinx.coroutines.flow.Flow

class PopularMoviesUseCase (
    private val repository: AppRepository
) {

    fun getPopularMovies(pageValue:Int) : Flow<Resource<PopularMoviesResponseModel>> {
        return repository.getPopularMovies(pageValue)
    }
}