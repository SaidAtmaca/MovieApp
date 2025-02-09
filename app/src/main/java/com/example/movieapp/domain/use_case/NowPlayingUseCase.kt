package com.example.movieapp.domain.use_case

import com.example.common.utils.Resource
import com.example.movieapp.data.model.NowPlayingResponseModel
import com.example.movieapp.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class NowPlayingUseCase(
    private val repository: AppRepository
) {

    fun getNowPlayingMovies(pageValue:Int) : Flow<Resource<NowPlayingResponseModel>>{
        return repository.getNowPlaying(pageValue)
    }
}