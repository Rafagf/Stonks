package com.rafag.stonks.android.theming

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

object StonksColors {
    val blue400 = Color(0xFF5F69C6)
    val purple400 = Color(0xFF885fc6)
    val yellow600 = Color(0xFFbcc03a)
    val yellow900 = Color(0xFF7c6f22)
    val red700 = Color(0xFFc94043)
    val green400 = Color(0xFF16CC64)
    val white = Color(0xFFFFFFFF)
    val black600 = Color(0xFF293749)
    val black200 = Color(0xFF001122)
    val gray200 = Color(0xFFd6d8d8)
    val gray600 = Color(0xFF6a6c6c)
}

val lightColors = lightColors(
    primary = StonksColors.blue400,
    primaryVariant = StonksColors.purple400,
    secondary = StonksColors.yellow600,
    secondaryVariant = StonksColors.yellow900,
    background = StonksColors.white,
    surface = StonksColors.white,
    error = StonksColors.red700,
    onPrimary = StonksColors.white,
    onSecondary = StonksColors.white,
    onBackground = StonksColors.blue400,
    onSurface = StonksColors.blue400,
    onError = StonksColors.white,
)

val darkColors = darkColors(
    primary = StonksColors.blue400,
    primaryVariant = StonksColors.purple400,
    secondary = StonksColors.yellow600,
    secondaryVariant = StonksColors.yellow900,
    background = StonksColors.black200,
    surface = StonksColors.blue400,
    error = StonksColors.red700,
    onPrimary = StonksColors.white,
    onSecondary = StonksColors.white,
    onBackground = StonksColors.white,
    onSurface = StonksColors.white,
    onError = StonksColors.white,
)