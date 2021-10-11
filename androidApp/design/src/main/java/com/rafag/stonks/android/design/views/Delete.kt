package com.rafag.stonks.android.design.views

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rafag.stonks.android.design.theming.StonksColors

@Composable
fun Delete(modifier: Modifier) {
    Icon(
        Filled.Delete,
        contentDescription = "",
        tint = StonksColors.gray600,
        modifier = modifier
            .size(32.dp)
    )
}