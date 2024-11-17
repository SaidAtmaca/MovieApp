package com.example.movieapp.presentation.ui.detailScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.common.GlobalValues
import com.example.movieapp.core.common.enums.UIEvent
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.data.model.MovieDetailModel
import com.example.movieapp.domain.use_case.MovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase
)  : ViewModel(){

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var job: Job? = null

    private val _movieInfo : MutableState<MovieDetailModel?> = mutableStateOf(null)
    val movieInfo : State<MovieDetailModel?> = _movieInfo

    private fun setMovieInfo(model:MovieDetailModel?){
        _movieInfo.value=model
    }


    fun getMovieDetails(movieId:Int){
        job = viewModelScope.launch {
            movieDetailUseCase.getMovieDetails(movieId)
                .onEach {result->
                    when (result) {

                        is Resource.Success -> {
                            GlobalValues.showLoading.postValue(false)

                            Log.e("responsee",result.data.toString())

                            result.data?.let {
                               Log.e("detailss",it.toString())
                                setMovieInfo(it)

                            }





                        }

                        is Resource.Error -> {
                            GlobalValues.showLoading.postValue(false)
                            _eventFlow.emit(
                                UIEvent.ShowSnackbar(
                                    result.message ?: "Unknown error"
                                )
                            )
                        }

                        is Resource.Loading -> {
                            GlobalValues.showLoading.postValue(true)
                        }
                    }
                }.launchIn(this)
        }
    }

}