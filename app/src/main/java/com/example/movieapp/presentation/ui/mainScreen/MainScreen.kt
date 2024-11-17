package com.example.movieapp.presentation.ui.mainScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.movieapp.R
import com.example.movieapp.core.common.enums.UIEvent
import com.example.movieapp.presentation.components.AppTopBar
import com.example.movieapp.presentation.components.MovieCard
import com.example.movieapp.presentation.components.MoviePagePassComponent
import com.example.movieapp.presentation.util.Screen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


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

    LaunchedEffect(key1 = viewModel.pageCount.value) {

        viewModel.setBackEnabled(viewModel.pageCount.value>=2)
        viewModel.getNowPlayingList(viewModel.pageCount.value)


    }

    LaunchedEffect(viewModel.movieList.size) {
        viewModel.setForwardEnabled(viewModel.movieList.isNotEmpty())
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            AppTopBar(
                title = stringResource(R.string.anasayfa)
            ) {
                scope.launch {
                    if (drawerState.isOpen){
                        drawerState.close()
                    }else{
                        drawerState.open()
                    }
                }
            }
        },
        bottomBar = {
            MoviePagePassComponent(
                forwardClicked = {
                    viewModel.plusPageCount()
                },
                backClicked = {
                    viewModel.lessPageCount()
                },
                forwardEnabled = viewModel.forwardEnabled,
                backEnabled = viewModel.backEnabled
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AnimatedVisibility(visible = viewModel.movieList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)){
                        items(viewModel.movieList) { eachMovie ->

                           MovieCard(model = eachMovie) { clickedMovie ->

                               navController.currentBackStackEntry?.savedStateHandle?.set("movieId",clickedMovie.id)
                               navController.navigate(Screen.DetailScreen.route)
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

        Text(text = stringResource(R.string.dataBulunamadi))
    }
}