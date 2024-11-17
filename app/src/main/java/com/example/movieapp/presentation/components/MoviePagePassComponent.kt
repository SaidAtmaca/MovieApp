package com.example.movieapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.presentation.ui.theme.CornerRound

@Composable
fun MoviePagePassComponent(
    forwardClicked :()->Unit,
    backClicked :()->Unit,
    forwardEnabled : State<Boolean>,
    backEnabled : State<Boolean>
) {

    Row(
        modifier = Modifier.fillMaxWidth().height(intrinsicSize = IntrinsicSize.Max).padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth().weight(1f)
                .wrapContentHeight()
                .clickable(
                    enabled = backEnabled.value,
                    onClick = {
                        backClicked()
                    }
                ),
            shape = RoundedCornerShape(CornerRound.smallCurveRound),
            colors = CardDefaults.cardColors(containerColor = if (backEnabled.value) Color.White else Color.Gray),
            border = BorderStroke(0.5.dp,Color.Gray)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp).padding(5.dp))

                Text(text = stringResource(R.string.geri),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(2.dp))
            }
        }

        Card(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth().weight(1f)
                .wrapContentHeight()

                .clickable(
                    enabled = forwardEnabled.value,
                    onClick = {
                        forwardClicked()
                    }
                ),
            shape = RoundedCornerShape(CornerRound.smallCurveRound),
            colors = CardDefaults.cardColors(containerColor = if (forwardEnabled.value) Color.White else Color.Gray),
            border = BorderStroke(0.5.dp,Color.Gray)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.ileri),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(2.dp))

                Icon(imageVector = Icons.AutoMirrored.Default.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier.size(24.dp).padding(5.dp))


            }
        }
    }

}