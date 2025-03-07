package com.example.presentation.ui.mainScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.GlobalValues
import com.example.common.enums.UIEvent
import com.example.common.utils.Resource
import com.example.domain.use_case.NowPlayingUseCase
import com.example.domain.use_case.UpComingMoviesUseCase
import com.example.model.MovieOverViewModel
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
    private val nowPlayingUseCase: NowPlayingUseCase,
    private val upComingMoviesUseCase: UpComingMoviesUseCase
)  : ViewModel(){

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    val movieList : SnapshotStateList<MovieOverViewModel> = mutableStateListOf()
    val upcomingMovieList : SnapshotStateList<MovieOverViewModel> = mutableStateListOf()

    private var job: Job? = null

    private val _pageCount : MutableState<Int> = mutableIntStateOf(1)
    val pageCount : State<Int> = _pageCount


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

    fun getUpComingMoviesList(pageValue:Int){
        job = viewModelScope.launch {
            upComingMoviesUseCase.getUpComingMovies(pageValue)
                .onEach {result->
                    when (result) {

                        is Resource.Success -> {
                            GlobalValues.showLoading.postValue(false)

                            Log.e("responsee",result.data.toString())

                            result.data?.let {

                                upcomingMovieList.clear()
                                upcomingMovieList.addAll(it.results)

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
        getUpComingMoviesList(_pageCount.value)
    }

}