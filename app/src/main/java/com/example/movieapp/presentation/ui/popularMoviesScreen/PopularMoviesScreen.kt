package com.example.movieapp.presentation.ui.popularMoviesScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.movieapp.R
import com.example.movieapp.core.common.enums.UIEvent
import com.example.movieapp.presentation.components.MovieCard
import com.example.movieapp.presentation.components.PageTopBar
import com.example.movieapp.presentation.ui.mainScreen.NoDataLayout
import com.example.movieapp.presentation.ui.theme.generalScreenBackgroundColor
import com.example.movieapp.presentation.util.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(navController: NavController,
                        drawerState: DrawerState,
                        viewModel: PopularMoviesScreenViewModel= hiltViewModel()
) {

    val scope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UIEvent.Navigate -> {
                    navController.navigate(event.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            inclusive=false
                        }
                    }
                }


                else -> {}
            }
        }

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

           PageTopBar(
               backClicked = {
                  viewModel.gotoMainScreen()
               },
               title = stringResource(R.string.popularMovies)
           )
        },
        bottomBar = {

        }
    ) {
        Box(modifier = Modifier.padding(it)){
            Column(
                modifier = Modifier.fillMaxSize().background(generalScreenBackgroundColor),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AnimatedVisibility(visible = viewModel.movieList.isNotEmpty()) {

                    val resfreshState = rememberPullToRefreshState()
                    var isRefreshing by remember {
                        mutableStateOf(false)
                    }

                    PullToRefreshBox(
                        isRefreshing = isRefreshing,
                        state = resfreshState,
                        onRefresh = {

                            scope.launch {
                                isRefreshing=true
                                delay(2.seconds)
                                isRefreshing=false
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    ) {

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()){
                            items(viewModel.movieList) { eachMovie ->

                                MovieCard(model = eachMovie) { clickedMovie ->

                                    navController.currentBackStackEntry?.savedStateHandle?.set("movieId",clickedMovie.id)
                                    navController.navigate(Screen.DetailScreen.route)
                                }
                            }

                        }
                    }



                }


                AnimatedVisibility(visible = viewModel.movieList.isEmpty()) {

                    NoDataLayout()
                }

            }
        }

    }

}