package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.common.GlobalValues
import com.example.movieapp.ui.MovieApp
import com.example.presentation.components.LoadingDialog
import com.example.presentation.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)





        setContent {
            MovieAppTheme {

                var showLoading by remember {
                    mutableStateOf(false)
                }

                if (showLoading){

                    LoadingDialog {
                        GlobalValues.showLoading.postValue(false)
                    }
                }

                GlobalValues.showLoading.observe(this@MainActivity){
                    showLoading=it
                }

                MovieApp()

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieAppTheme {
        Greeting("Android")
    }
}