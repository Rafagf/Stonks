package com.rafag.stonks.android.design.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rafag.stonks.android.design.R
import com.rafag.stonks.android.design.theming.StonksText

@Composable
fun Error(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StonksText.BodyMediumBold(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp),
            color = MaterialTheme.colors.error,
            text = stringResource(id = R.string.error_title),
        )
        Icon(
            Filled.Refresh,
            contentDescription = "",
            tint = MaterialTheme.colors.error,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Preview
@Composable
fun ErrorPreview() {
    MaterialTheme {
        Error {

        }
    }
}
