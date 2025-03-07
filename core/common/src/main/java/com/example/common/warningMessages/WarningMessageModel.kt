package com.example.common.warningMessages


data class WarningMessageModel(
    val code : String = "",
    val message : String ="",
    val type : Int = 0,
    val source : String = ErrorSource.MOBILE.value,
    val title : String = ""
)
