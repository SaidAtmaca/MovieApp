package com.example.movieapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieapp.core.common.Constants.ROOM_USER_TABLE
import java.io.Serializable

@Entity(tableName = ROOM_USER_TABLE)

data class User(
    @PrimaryKey
    var UserID: Long = 0,
    var userName: String = "",
    var sessionKey: String = ""


) : Serializable




















