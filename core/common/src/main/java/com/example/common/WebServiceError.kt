package com.example.common

import com.google.gson.annotations.SerializedName

data class WebServiceError(
    @SerializedName("ErrorCode")
    var errorCode: Int = 0,
    @SerializedName("ErrorMessageStr")
    var errorMessageStr: String = ""
)