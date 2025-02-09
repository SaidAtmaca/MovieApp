package com.example.presentation.ui.detailScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.common.Constants
import com.example.common.enums.UIEvent
import com.example.presentation.components.BigImageBox
import com.example.presentation.ui.mainScreen.NoDataLayout
import com.example.presentation.ui.theme.CornerRound
import com.example.presentation.ui.theme.discoverButtonColor
import com.example.presentation.R
import kotlinx.coroutines.flow.collectLatest


@Composable
fun DetailScreen(
    navController: NavController,
    drawerState: DrawerState,
    movieId:Int?,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        movieId?.let {
            viewModel.getMovieDetails(it)
        }
    }



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
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)){

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(0f)
                ) {
                    AnimatedVisibility(visible =viewModel.movieInfo.value?.backdropPath != null) {

                        val url = Constants.IMAGE_BASE_URL+viewModel.movieInfo.value?.backdropPath

                        BigImageBox(url)
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(0f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(36.dp)
                            .padding(start = 10.dp, top = 10.dp).clickable {
                                viewModel.gotoMainScreen()
                            },
                        tint = if (viewModel.movieInfo.value?.backdropPath != null) Color.White else Color.Black
                    )

                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Button(
                    onClick = {
                        viewModel.gotoPopularMovies()
                    },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = discoverButtonColor),
                    shape = RoundedCornerShape(CornerRound.mediumCurveRound)

                ){

                    Text(text = stringResource(R.string.discoverPopularMovie),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                }
            }
        }
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)){

            AnimatedVisibility(visible = viewModel.movieInfo.value != null) {
                Column(
                        modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {


                 RateAndReleaseDateComponent(
                     rate = viewModel.movieInfo.value?.voteAverage ?: 0.0,
                     releaseDate = viewModel.movieInfo.value?.releaseDate ?:""
                 )

                 TitleComponent(
                     value = viewModel.movieInfo.value?.title?:"",
                     dateYear = viewModel.movieInfo.value?.releaseDate?.split("-")?.first() ?: ""
                 )

                    Column(
                        modifier = Modifier.fillMaxSize().weight(1f).padding(10.dp),

                    ) {

                        Text( text = viewModel.movieInfo.value?.overview ?: "",
                            style = MaterialTheme.typography.bodyLarge,
                            overflow = TextOverflow.Ellipsis)
                    }


            }
            }

            AnimatedVisibility(visible = viewModel.movieInfo.value == null) {

                NoDataLayout()
            }

        }
    }






}

@Composable
fun RateAndReleaseDateComponent(
    rate:Double,
    releaseDate:String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Image(
            painter = painterResource(R.drawable.star_icon),
            contentDescription = "",
            modifier = Modifier
                .size(16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {

            Text(text =rate.toString(), color = Color.Black,
            style = MaterialTheme.typography.titleSmall)

            Text(text = stringResource(R.string.topRate),
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall)
        }

        Image(
            painter = painterResource(R.drawable.date_icon),
            contentDescription = "",
            modifier = Modifier
                .size(8.dp)
        )

        Text(text =releaseDate,
            color = Color.Black,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 5.dp)
        )

    }

}


@Composable
fun TitleComponent(
    value:String,
    dateYear:String
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text =value, color = Color.Black,
            style = MaterialTheme.typography.titleMedium)

        AnimatedVisibility(visible = dateYear.isNotEmpty()) {
            Text(text ="($dateYear)", color = Color.Black,
                style = MaterialTheme.typography.titleMedium)
        }

    }

}