package com.example.movieapp.presentation.util


sealed class Screen(val route : String){

    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
    object PopularMoviesScreen : Screen("popular_movies_screen")





}
