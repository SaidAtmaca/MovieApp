package com.example.movieapp.data.model

import java.io.Serializable

data class UpComingMoviesResponseModel(
    var dates : DatesModel,
    var page : Int,
    var results : List<MovieOverViewModel>,
): Serializable