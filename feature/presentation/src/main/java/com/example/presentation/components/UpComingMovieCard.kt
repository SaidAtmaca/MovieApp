package com.example.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.common.Constants.IMAGE_BASE_URL
import com.example.model.MovieOverViewModel
import com.example.presentation.ui.theme.viewPagerActiveColor
import com.example.presentation.ui.theme.viewPagerPassiveColor
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.launch


@Preview(showBackground = true)
@Composable
private fun UpComingMoviePrev() {
    val model = MovieOverViewModel(
        adult = false,
        backdropPath = "/ljNVBysYnPCwLqP3HXL7T1thZxu.jpg",
        genreIds = listOf(18,14,10749),
        id = 402431,
        originalLanguage = "en",
        originalTitle = "Wicked",
        overview = "Elphaba, an ostracized but defiant girl born with green skin, and Glinda, a privileged aristocrat born popular, become extremely unlikely friends in the magical Land of Oz. As the two girls struggle with their opposing personalities, their friendship is tested as both begin to fulfill their destinies as Glinda the Good and The Wicked Witch of the West.",
        popularity = 426.578,
        posterPath = "/c5Tqxeo1UpBvnAc3csUm7j3hlQl.jpg",
        releaseDate = "2024-11-20",
        title = "Wicked",
        video = false,
        voteAverage = 7.8,
        voteCount = 17
    )
    Column(Modifier.fillMaxSize()) {


    }


}




@Composable
fun BigImageBox(url: String) {
    val painter = rememberAsyncImagePainter(url)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painter,
                contentDescription = "Loaded Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )


            AnimatedVisibility(visible =painter.state is coil.compose.AsyncImagePainter.State.Loading ) {
                CircularProgressIndicator(color = Color.Gray)
            }

        }

    }


}



@Composable
fun UpComingMoviePager(list: List<MovieOverViewModel>,
                       movieClicked :(MovieOverViewModel)->Unit) {
    val pagerState = rememberPagerState{list.size}
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.padding(0.dp)
    ){
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(0.dp)) { page ->
            val model = list.get(page)
            val url = IMAGE_BASE_URL+model.backdropPath
            val dateYear = model.releaseDate.split("-").first()

            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    movieClicked(list.get(page))
                }
                .height(200.dp)){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(0f)
                ) {
                    BigImageBox(url)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(text = if (dateYear.isEmpty()) model.title else "${model.title} ($dateYear)",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(5.dp),
                        color = Color.White)

                    Text(text = model.overview,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White)

                    HorizontalPagerIndicator(
                        pageCount = list.size,
                        pagerState = pagerState,
                        modifier = Modifier.padding(5.dp).clickable {
                            val currentPage = pagerState.currentPage
                            val totalPages = list.size
                            val nextPage = if (currentPage < totalPages - 1) currentPage + 1 else 0
                            coroutineScope.launch { pagerState.animateScrollToPage(nextPage) }
                        },
                        activeColor = viewPagerActiveColor,
                        inactiveColor = viewPagerPassiveColor

                    )

                }

            }
            LaunchedEffect(pagerState) {
                snapshotFlow { pagerState.currentPage }
                    .collect { currentPage ->
                        pagerState.animateScrollToPage(currentPage)
                    }
            }
        }
    }

}