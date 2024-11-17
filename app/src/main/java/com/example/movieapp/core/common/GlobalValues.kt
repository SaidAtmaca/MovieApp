package com.example.movieapp.core.common

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.core.warningMessages.WarningMessageModel

object GlobalValues {



    var showLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    var appUIEvent : MutableLiveData<WarningMessageModel?> = MutableLiveData(null)




}