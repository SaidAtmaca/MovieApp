package com.example.presentation.ui.mainScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.common.enums.UIEvent
import com.example.presentation.components.MovieCard
import com.example.presentation.components.UpComingMoviePager
import com.example.presentation.ui.theme.generalScreenBackgroundColor
import com.example.movieapp.presentation.util.Screen
import com.example.presentation.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    drawerState: DrawerState,
    viewModel: MainScreenViewModel = hiltViewModel()
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

                UpComingMoviePager(viewModel.upcomingMovieList,
                    movieClicked = { clickedMovie->
                        navController.currentBackStackEntry?.savedStateHandle?.set("movieId",clickedMovie.id)
                        navController.navigate(Screen.DetailScreen.route)
                    })



        },
        bottomBar = {
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)){
            Column(
                modifier = Modifier.fillMaxSize().background(generalScreenBackgroundColor),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val resfreshState = rememberPullToRefreshState()
                var isRefreshing by remember {
                    mutableStateOf(false)
                }


                AnimatedVisibility(visible = viewModel.movieList.isNotEmpty()) {
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

@Composable
fun NoDataLayout() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(painter = painterResource(R.drawable.empty_box),
            contentDescription = "",
            modifier = Modifier.size(36.dp))

        Text(text = stringResource(R.string.dataBulunamadi))
    }
}