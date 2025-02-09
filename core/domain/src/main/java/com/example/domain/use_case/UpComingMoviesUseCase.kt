package com.example.domain.use_case

import com.example.common.utils.Resource
import com.example.domain.repository.AppRepository
import com.example.model.UpComingMoviesResponseModel
import kotlinx.coroutines.flow.Flow

class UpComingMoviesUseCase(
    private val repository: AppRepository
) {

    fun getUpComingMovies(pageValue:Int) : Flow<Resource<UpComingMoviesResponseModel>> {
        return repository.getupComingMovies(pageValue)
    }
}