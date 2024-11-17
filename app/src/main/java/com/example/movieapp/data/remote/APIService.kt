package com.example.movieapp.data.remote

import com.example.movieapp.data.model.MovieDetailModel
import com.example.movieapp.data.model.NowPlayingResponseModel
import com.example.movieapp.data.model.PopularMoviesResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {


    @GET("3/movie/now_playing?language=en-US")
    suspend fun getNowPlayingMovies(@Query("page") pageValue:Int): NowPlayingResponseModel


    @GET("3/movie/{movie_id}?language=en-US")
    suspend fun getMovieDetail(@Path("movie_id") movieId:Int): MovieDetailModel

    @GET("3/movie/popular?language=en-US")
    suspend fun getPopularMovies(@Query("page") pageValue:Int): PopularMoviesResponseModel

}