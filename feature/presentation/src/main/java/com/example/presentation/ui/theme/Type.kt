package com.example.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.presentation.R

// Set of Material typography styles to start with

val robotoFontFamily = FontFamily(
    Font(R.font.roboto_thin, FontWeight.Thin),
    Font(R.font.roboto_light,FontWeight.Light),
    Font(R.font.roboto_regular,FontWeight.Normal),
    Font(R.font.roboto_medium,FontWeight.Medium),
    Font(R.font.roboto_bold,FontWeight.SemiBold),
    Font(R.font.roboto_bold,FontWeight.Bold),
    Font(R.font.roboto_italic,FontWeight.Normal),
    Font(R.font.roboto_bold_italic,FontWeight.Bold),
)

val Typography = Typography(
    titleSmall = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.roboto_bold,FontWeight.Bold),
        ),
        fontSize = 14.sp,
        lineHeight = 14.sp
    ),
    titleMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 20.sp
    ),
    bodySmall =  TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 10.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium =  TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 12.sp
    ),
    bodyLarge =  TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 14.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 10.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 12.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 12.sp
    ),
    displaySmall = TextStyle(
        fontFamily = robotoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 10.sp
    ),




    )