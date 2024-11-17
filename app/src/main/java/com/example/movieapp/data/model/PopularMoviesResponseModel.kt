package com.example.movieapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PopularMoviesResponseModel(
    var page:Int,
    var results : List<MovieOverViewModel>,
    @SerializedName("total_pages") val totalPages:Long,
    @SerializedName("total_results") val totalResults:Long,

):Serializable
