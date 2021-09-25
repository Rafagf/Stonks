package com.rafag.stonks.android.design

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun TintStatusBar(color: Color = MaterialTheme.colors.primaryVariant) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = color)
}