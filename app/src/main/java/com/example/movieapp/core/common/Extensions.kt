package com.example.movieapp.core.common

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import com.example.movieapp.core.WebServiceError
import com.example.movieapp.core.gsonAdapters.DoubleDeserializer
import com.example.movieapp.core.gsonAdapters.IntegerDeserializer
import com.example.movieapp.core.gsonAdapters.LongDeserializer
import com.example.movieapp.core.utils.ResponseResult
import com.example.movieapp.core.warningMessages.ErrorSource
import com.example.movieapp.core.warningMessages.ErrorTypes
import com.example.movieapp.core.warningMessages.WarningMessageModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken


inline fun <reified T> SnapshotStateList<T>.toArrayList() : ArrayList<T>{
    var tempList = arrayListOf<T>()

    tempList.addAll(this)

    return tempList
}


inline fun <reified T> String.toListByGson(): ArrayList<T> = if (isNotEmpty()) {
    Gson().fromJson(this, TypeToken.getParameterized(ArrayList::class.java, T::class.java).type)
} else {
    arrayListOf()
}



inline fun <reified T> String.jsonStringToModelFromApi(isList:Boolean=true): ResponseResult<T> {



    val gson = GsonBuilder()
        .registerTypeAdapter(Int::class.java, IntegerDeserializer())
        .registerTypeAdapter(Int::class.javaObjectType, IntegerDeserializer())
        .registerTypeAdapter(Long::class.java, LongDeserializer())
        .registerTypeAdapter(Long::class.javaObjectType, LongDeserializer())
        .registerTypeAdapter(Double::class.java, DoubleDeserializer())
        .registerTypeAdapter(Double::class.javaObjectType, DoubleDeserializer())
        .create()

    return if (contains("ErrorCode") && (gson.fromJson(this, JsonArray::class.java).size() <=1)) {
        val jsonArray = gson.fromJson(this, JsonArray::class.java)
        if (jsonArray.size() > 0) {
            val firstElement = jsonArray[0]
            val errorModel: WebServiceError =
                gson.fromJson(firstElement, WebServiceError::class.java)

            Log.e("errormodelll",errorModel.toString())
            if (errorModel.errorCode != -1808 && errorModel.errorCode != -130116 ){

                Log.e("t Class",T::class.toString())




                        GlobalValues.appUIEvent.postValue(
                            WarningMessageModel(code = "",
                                message = errorModel.errorMessageStr,
                                type = ErrorTypes.FAILED.value,
                                source = ErrorSource.API.value,
                                title = errorModel.errorCode.toString()
                            )
                        )








            }


            ResponseResult(emptyList(), true, errorModel.errorMessageStr, errorCode = errorModel.errorCode?.toLong() ?: 0L)
        } else {
            ResponseResult(emptyList(), true, "Geçersiz JSON biçimi")
        }
    }else if (this.isNotEmpty()) {


        if (isList){
            val dataList = gson.fromJson<List<T>>(
                this,
                object : TypeToken<List<T>>() {}.type
            )
            Log.e("listSize",dataList.size.toString())
            ResponseResult(dataList, false, "")
        }else{
            val data = gson.fromJson<T>(
                this,
                object : TypeToken<T>() {}.type
            )
            val tempList= listOf<T>(data)


            ResponseResult(tempList, false, "")
        }

    } else {
        ResponseResult(arrayListOf(), false, "")
    }
}


fun stringToListOfNumbers(input: String): List<Int> {
    // Eğer giriş string'i boşsa boş bir liste döndür
    if (input.isBlank()) return emptyList()

    return try {
        // String'i virgüllere göre böl ve her bir parçayı Int'e dönüştür
        input.split(",").map { it.toInt() }
    } catch (e: NumberFormatException) {
        // Eğer bir parça Int'e dönüştürülemiyorsa boş bir liste döndür
        emptyList()
    }
}




@Composable
fun <viewModel : LifecycleObserver> viewModel.observeLifecycleEvents(lifecycle: Lifecycle) {
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(this@observeLifecycleEvents)
        onDispose {
            lifecycle.removeObserver(this@observeLifecycleEvents)
        }
    }
}

