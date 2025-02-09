package com.example.domain.use_case

import com.example.common.utils.Resource
import com.example.domain.repository.AppRepository
import com.example.model.NowPlayingResponseModel
import kotlinx.coroutines.flow.Flow

class NowPlayingUseCase(
    private val repository: AppRepository
) {

    fun getNowPlayingMovies(pageValue:Int) : Flow<Resource<NowPlayingResponseModel>>{
        return repository.getNowPlaying(pageValue)
    }
}