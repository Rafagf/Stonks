package com.rafag.stonks.android.theming

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

object StonksColors {
    val yellow600 = Color(0xFFbba353)
    val red400 = Color(0xFFc6233e)
    val green400 = Color(0xFF82cf63)
    val white = Color(0xFFFFFFFF)
    val black200 = Color(0xFF001122)
    val gray200 = Color(0xFFd6d8d8)
    val gray600 = Color(0xFF6a6c6c)
}

val lightColors = lightColors(
    primary = StonksColors.yellow600,
    primaryVariant = StonksColors.yellow600,
    secondary = StonksColors.yellow600,
    secondaryVariant = StonksColors.yellow600,
    background = StonksColors.white,
    surface = StonksColors.white,
    error = StonksColors.red400,
    onPrimary = StonksColors.white,
    onSecondary = StonksColors.white,
    onBackground = StonksColors.yellow600,
    onSurface = StonksColors.yellow600,
    onError = StonksColors.white,
)

val darkColors = darkColors(
    primary = StonksColors.yellow600,
    primaryVariant = StonksColors.yellow600,
    secondary = StonksColors.yellow600,
    secondaryVariant = StonksColors.yellow600,
    background = StonksColors.black200,
    surface = StonksColors.white,
    error = StonksColors.red400,
    onPrimary = StonksColors.white,
    onSecondary = StonksColors.white,
    onBackground = StonksColors.yellow600,
    onSurface = StonksColors.yellow600,
    onError = StonksColors.white,
)