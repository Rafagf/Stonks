package com.rafag.stonks.android.design.theming

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

object StonksText {

    @Composable
    fun BodyBig(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign? = null,
        color: Color = MaterialTheme.colors.onSurface,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            modifier = modifier,
            textAlign = textAlign,
            color = color,
            maxLines = maxLines,
        )
    }

    @Composable
    fun BodyBigBold(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign? = null,
        color: Color = MaterialTheme.colors.onSurface,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3.withFontWeight(FontWeight.ExtraBold),
            modifier = modifier,
            textAlign = textAlign,
            color = color,
            maxLines = maxLines,
        )
    }

    @Composable
    fun BodyMedium(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign? = null,
        color: Color = MaterialTheme.colors.onSurface,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = modifier,
            textAlign = textAlign,
            color = color,
            maxLines = maxLines,
        )
    }

    @Composable
    fun BodyMediumBold(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign? = null,
        color: Color = MaterialTheme.colors.onSurface,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1.withFontWeight(FontWeight.ExtraBold),
            modifier = modifier,
            textAlign = textAlign,
            color = color,
            maxLines = maxLines,
        )
    }

    @Composable
    fun BodySmall(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign? = null,
        color: Color = MaterialTheme.colors.onSurface,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            modifier = modifier,
            textAlign = textAlign,
            color = color,
            maxLines = maxLines,
        )
    }

    @Composable
    fun BigButtonBold(
        text: String,
        modifier: Modifier = Modifier,
        textAlign: TextAlign? = null,
        color: Color = MaterialTheme.colors.onSurface,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3.withFontWeight(FontWeight.ExtraBold),
            modifier = modifier,
            textAlign = textAlign,
            color = color,
            maxLines = maxLines,
        )
    }
}

fun TextStyle.withFontWeight(fontWeight: FontWeight) = this.copy(
    fontWeight = fontWeight
)

