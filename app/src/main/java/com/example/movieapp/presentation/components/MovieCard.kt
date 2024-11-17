package com.example.movieapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.movieapp.data.model.MovieOverViewModel
import com.example.movieapp.presentation.ui.theme.CornerRound


@Composable
fun MovieCard(model:MovieOverViewModel,
              onMovieClicked : (MovieOverViewModel)->Unit) {

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(5.dp)
            .clickable {
                onMovieClicked(model)
            },
        shape = RoundedCornerShape(CornerRound.smallCurveRound),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(0.5.dp,Color.Gray)

    ){
        Column(
            modifier = Modifier.fillMaxSize().padding(5.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text( text = model.originalTitle,
                style = MaterialTheme.typography.bodyMedium)

            Text(text = model.releaseDate,
                style = MaterialTheme.typography.bodySmall)
        }
    }
}