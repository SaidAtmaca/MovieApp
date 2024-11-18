package com.example.movieapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.movieapp.presentation.ui.theme.generalScreenBackgroundColor

@Preview
@Composable
private fun PageTopBarPrev() {

    PageTopBar(backClicked = {

    },
        title = "Popular Movies")
}


@Composable
fun PageTopBar(
    backClicked :()->Unit,
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .background(generalScreenBackgroundColor)
            .zIndex(0f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {


        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .size(40.dp)
                .padding(start = 15.dp)
                .clickable { backClicked() })

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
                modifier = Modifier.padding(start = 10.dp))


        }

        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = "",
            tint = Color.Transparent,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 15.dp))





    }


}