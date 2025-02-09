package com.example.model

import java.io.Serializable

data class NowPlayingResponseModel(
    var dates : DatesModel,
    var page : Int,
    var results : List<MovieOverViewModel>,
):Serializable
