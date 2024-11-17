package com.example.movieapp.presentation.ui.detailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.presentation.components.AppTopBar
import kotlinx.coroutines.launch


@Composable
fun DetailScreen(
    navController: NavController,
    drawerState: DrawerState,
    movieId:Int?,
    viewModel: DetailScreenViewModel= hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        movieId?.let {
            viewModel.getMovieDetails(it)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(
                title = stringResource(R.string.detaySafasi)
            ) {
                scope.launch {
                    if (drawerState.isOpen){
                        drawerState.close()
                    }else{
                        drawerState.open()
                    }
                }
            }
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it)){


            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = viewModel.movieInfo.value.toString())
            }
        }
    }






}