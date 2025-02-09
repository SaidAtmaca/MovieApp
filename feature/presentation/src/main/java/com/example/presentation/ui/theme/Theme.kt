package com.example.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    tertiary = tertiaryColor,
    background = backgroundColor,
    surface = surfaceColor,
    onPrimary = onPrimaryColor,
    onSecondary = onSecondaryColor,
    onTertiary = onTertiaryColor,
    onBackground = onBackgroundColor,
    onSurface = onSurfaceColor,
    error = errorColor,
    onError = onErrorColor
)

@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }



    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}