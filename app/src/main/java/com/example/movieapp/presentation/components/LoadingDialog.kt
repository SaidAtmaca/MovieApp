package com.example.movieapp.presentation.components

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.movieapp.R

@Composable
fun LoadingDialog(
    onDismissRequest : () ->Unit
) {
    Dialog(onDismissRequest = {onDismissRequest()
    },
        properties = DialogProperties(dismissOnBackPress = true,
            dismissOnClickOutside = true,
            decorFitsSystemWindows = true)
    ){

        val context = LocalContext.current



        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
        val progress by animateLottieCompositionAsState(composition)
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }

}