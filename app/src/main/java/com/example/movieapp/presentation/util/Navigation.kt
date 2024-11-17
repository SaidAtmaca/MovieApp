package com.example.movieapp.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.presentation.ui.detailScreen.DetailScreen
import com.example.movieapp.presentation.ui.mainScreen.MainScreen
import com.example.movieapp.presentation.ui.popularMoviesScreen.PopularMoviesScreen


@Composable
fun Navigation(
    navController: NavHostController,
    drawerState: DrawerState,
) {


    NavHost(navController = navController, startDestination = Screen.MainScreen.route,
        modifier = Modifier.fillMaxSize()){

        composable(Screen.MainScreen.route){
            MainScreen(navController = navController,
                drawerState=drawerState)
        }

        composable(Screen.DetailScreen.route){

            val movieId = navController.previousBackStackEntry?.savedStateHandle?.get<Int>("movieId")

            DetailScreen(navController = navController,
                drawerState=drawerState,
                movieId = movieId)
        }

        composable(Screen.PopularMoviesScreen.route){

            PopularMoviesScreen(navController = navController,
                drawerState=drawerState)
        }





    }
}