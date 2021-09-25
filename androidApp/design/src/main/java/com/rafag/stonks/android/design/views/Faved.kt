package com.rafag.stonks.android.design.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rafag.stonks.android.design.theming.StonksColors

@Composable
fun Faved() {
    Icon(
        Filled.Favorite,
        contentDescription = "",
        tint = StonksColors.red400,
        modifier = Modifier
            .padding(15.dp)
            .size(24.dp)
    )
}

@Composable
fun NotFaved() {
    Icon(
        Filled.Favorite,
        contentDescription = "",
        tint = StonksColors.gray200,
        modifier = Modifier
            .padding(16.dp)
            .size(24.dp)
    )
}

