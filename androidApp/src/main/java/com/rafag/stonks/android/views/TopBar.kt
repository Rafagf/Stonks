package com.rafag.stonks.android.views

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.*
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.rafag.stonks.android.R.*

@Preview
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(string.app_name), fontSize = 18.sp) },
        backgroundColor = Companion.Blue,
        contentColor = White,
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}