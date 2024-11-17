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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.movieapp.presentation.ui.theme.primaryColor


@Composable
fun AppTopBar(
    title:String,
    onMenuClicked: () -> Unit
) {

    ToolBarComponent(title = title,
        onMenuClicked = onMenuClicked)

}


@Composable
fun ToolBarComponent(title: String, onMenuClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .background(primaryColor)
            .zIndex(0f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {


        Icon(imageVector = Icons.Default.Menu,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .size(40.dp)
                .padding(start = 15.dp)
                .clickable { onMenuClicked() })

        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(horizontal = 10.dp), verticalArrangement = Arrangement.Center) {
            Text(text = title,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
                modifier = Modifier.padding(start = 10.dp))


        }

        Icon(imageVector = Icons.Outlined.Face,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .size(40.dp)
                .padding(horizontal = 10.dp))





    }
}