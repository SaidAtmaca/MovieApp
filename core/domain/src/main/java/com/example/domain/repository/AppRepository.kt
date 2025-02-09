package com.example.domain.repository

import com.example.common.utils.Resource
import com.example.model.BelongsToCollectionModel
import com.example.model.MovieDetailModel
import com.example.model.MovieOverViewModel
import com.example.model.NowPlayingResponseModel
import com.example.model.PopularMoviesResponseModel
import com.example.model.UpComingMoviesResponseModel
import com.example.model.entity.User
import kotlinx.coroutines.flow.Flow


interface AppRepository {

    fun getNowPlaying(pageValue:Int) : Flow<Resource<NowPlayingResponseModel>>

    fun getupComingMovies(pageValue:Int) : Flow<Resource<UpComingMoviesResponseModel>>

    fun getMovieDetails(movieDetails:Int) : Flow<Resource<MovieDetailModel>>


    fun getPopularMovies(pageValue:Int) : Flow<Resource<PopularMoviesResponseModel>>


}