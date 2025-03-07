package com.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BelongsToCollectionModel(
    var id : Int,
    var name : String,
    @SerializedName("poster_path") val posterPath:String,
    @SerializedName("backdrop_path") val backDropPath:String,
):Serializable
