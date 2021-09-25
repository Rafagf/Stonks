package com.rafag.stonks.android.design.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rafag.stonks.android.design.theming.StonksColors
import com.rafag.stonks.android.design.theming.StonksText

@Composable
fun StonkQuote(isUp: Boolean, price: String, change: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(if (isUp) StonksColors.green400 else StonksColors.red400)
            .padding(4.dp)
    ) {
        StonksText.BodyMediumBold(
            modifier = Modifier.align(CenterHorizontally),
            color = MaterialTheme.colors.quotesTextColor,
            text = price,
        )
        StonksText.BodySmall(
            modifier = Modifier.align(CenterHorizontally),
            color = MaterialTheme.colors.quotesTextColor,
            text = change,
        )
    }
}

private val Colors.quotesTextColor: Color
    @Composable get() = StonksColors.white