package com.example.common.utils


data class ResponseResult<T>(
    val data: List<T>,
    val isError: Boolean,
    val errorText : String = "",
    val errorCode : Long = -1L
)