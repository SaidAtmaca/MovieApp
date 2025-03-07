package com.example.data.repository

import android.util.Log
import com.example.common.utils.Resource
import com.example.domain.repository.AppRepository
import com.example.model.BelongsToCollectionModel
import com.example.model.MovieDetailModel
import com.example.model.MovieOverViewModel
import com.example.model.NowPlayingResponseModel
import com.example.model.PopularMoviesResponseModel
import com.example.model.UpComingMoviesResponseModel
import com.example.model.entity.User
import com.example.network.remote.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class AppRepositoryImpl(
    private val apiService: APIService
): AppRepository {


    override fun getNowPlaying(pageValue: Int): Flow<Resource<NowPlayingResponseModel>> = flow{
        emit(Resource.Loading())

        try {

            val response = apiService.getNowPlayingMovies(pageValue)
            emit(Resource.Success(response))

        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    override fun getupComingMovies(pageValue: Int): Flow<Resource<UpComingMoviesResponseModel>> =flow{
        emit(Resource.Loading())

        try {

            val response = apiService.getUpComingMovies(pageValue)
            emit(Resource.Success(response))

        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<Resource<MovieDetailModel>> = flow{
        emit(Resource.Loading())


        try {

            val response = apiService.getMovieDetail(movieId)
            Log.e("detailResponse",response.toString())
            emit(Resource.Success(response))

        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    override fun getPopularMovies(pageValue: Int): Flow<Resource<PopularMoviesResponseModel>> = flow{
        emit(Resource.Loading())
        try {

            val response = apiService.getPopularMovies(pageValue)
            emit(Resource.Success(response))

        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }



}