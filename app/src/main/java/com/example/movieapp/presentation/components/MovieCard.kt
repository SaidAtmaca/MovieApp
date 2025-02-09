package com.example.movieapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.common.Constants.IMAGE_BASE_URL
import com.example.movieapp.data.model.MovieOverViewModel
import com.example.movieapp.presentation.ui.theme.CornerRound


@Composable
fun MovieCard(model:MovieOverViewModel,
              onMovieClicked : (MovieOverViewModel)->Unit) {

    val url = IMAGE_BASE_URL+model.backdropPath
    val dateYear = model.releaseDate.split("-").first()
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(horizontal = 5.dp, vertical = 2.dp)
            .clickable {
                onMovieClicked(model)
            },
        shape = RoundedCornerShape(CornerRound.smallCurveRound),
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ){
        Row(
            modifier = Modifier.fillMaxSize().padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            ImageBox(url)

            Column(
                modifier = Modifier.fillMaxSize().weight(1f).padding(5.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text( text = if (dateYear.isEmpty())"${model.title}"  else "${model.title}(${dateYear})",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 5.dp))

                Text(text = model.overview,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium)
            }
        }

    }
}

@Composable
fun ImageBox(url: String) {
    val painter = rememberAsyncImagePainter(url)

    Card(
        modifier = Modifier.padding(5.dp),
        shape = RoundedCornerShape(CornerRound.smallCurveRound),
        colors = CardDefaults.cardColors(containerColor = Color.White),

    ) {
        Column(
            modifier = Modifier.size(120.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painter,
                contentDescription = "Loaded Image",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Crop
            )


            AnimatedVisibility(visible =painter.state is coil.compose.AsyncImagePainter.State.Loading ) {
                CircularProgressIndicator(color = Color.Gray)
            }

        }

    }


}