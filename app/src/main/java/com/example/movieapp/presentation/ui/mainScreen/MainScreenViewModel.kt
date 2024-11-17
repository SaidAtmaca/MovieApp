package com.example.movieapp.presentation.ui.mainScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.core.common.GlobalValues
import com.example.movieapp.core.common.enums.UIEvent
import com.example.movieapp.core.utils.Resource
import com.example.movieapp.data.model.MovieOverViewModel
import com.example.movieapp.domain.use_case.NowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val nowPlayingUseCase: NowPlayingUseCase
)  : ViewModel(){

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val movieList : SnapshotStateList<MovieOverViewModel> = mutableStateListOf()

    private var job: Job? = null

    private val _pageCount : MutableState<Int> = mutableIntStateOf(1)
    val pageCount : State<Int> = _pageCount

    fun plusPageCount(){
        _pageCount.value ++
    }

    fun lessPageCount(){
        if (_pageCount.value >=2){
            _pageCount.value--
        }
    }

    private val _backEnabled : MutableState<Boolean> = mutableStateOf(false)
    val backEnabled : State<Boolean> = _backEnabled

    private val _forwardEnabled : MutableState<Boolean> = mutableStateOf(true)
    val forwardEnabled : State<Boolean> = _forwardEnabled

    fun setBackEnabled(boolean: Boolean){
        _backEnabled.value=boolean
    }

    fun setForwardEnabled(boolean: Boolean){
        _forwardEnabled.value=boolean
    }

    fun getNowPlayingList(pageValue:Int){
        job = viewModelScope.launch {
            nowPlayingUseCase.getNowPlayingMovies(pageValue)
                .onEach {result->
                    when (result) {

                        is Resource.Success -> {
                            GlobalValues.showLoading.postValue(false)

                            Log.e("responsee",result.data.toString())

                            result.data?.let {
                                movieList.clear()
                                movieList.addAll(it.results)

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

    init {
        getNowPlayingList(_pageCount.value)
    }

}