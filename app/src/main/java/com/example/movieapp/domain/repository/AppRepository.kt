package com.example.movieapp.domain.repository

import com.example.movieapp.core.utils.Resource
import com.example.movieapp.data.local.entity.User
import com.example.movieapp.data.model.MovieDetailModel
import com.example.movieapp.data.model.NowPlayingResponseModel
import com.example.movieapp.data.model.PopularMoviesResponseModel
import kotlinx.coroutines.flow.Flow


interface AppRepository {



    fun getUser():Flow<User>

    fun insertUserFlow(user: User) : Flow<User>

    fun getNowPlaying(pageValue:Int) : Flow<Resource<NowPlayingResponseModel>>

    fun getMovieDetails(movieDetails:Int) : Flow<Resource<MovieDetailModel>>


    fun getPopularMovies(pageValue:Int) : Flow<Resource<PopularMoviesResponseModel>>






}