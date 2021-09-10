package com.rafag.stonks.android.search.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchStonkListItem(
    state: SearchStonkUiItem,
    onToggleFaved: (SearchStonkUiItem) -> Unit,
    onToggleUnfaved: (SearchStonkUiItem) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = {
                if (state.faved) onToggleUnfaved(state) else onToggleFaved(state)
            })
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(16.dp)
        ) {
            Text(text = state.name, fontSize = 18.sp, color = Color.Black)
            Text(text = state.symbol, fontSize = 14.sp, color = Color.Black)
        }
        if (state.faved) faved() else notFaved()

    }
}

@Composable
private fun faved() {
    Icon(
        Icons.Filled.Favorite,
        contentDescription = "",
        tint = Color.Red,
        modifier = Modifier
            .padding(15.dp)
            .size(24.dp)
    )
}

@Composable
private fun notFaved() {
    Icon(
        Icons.Filled.Favorite,
        contentDescription = "",
        tint = Color.LightGray,
        modifier = Modifier
            .padding(15.dp)
            .size(24.dp)
    )
}

data class SearchStonkUiItem(
    val name: String,
    val symbol: String,
    val faved: Boolean,
)

@Preview(showBackground = true)
@Composable
fun FavedStonkListItemPreview() {
    SearchStonkListItem(state = SearchStonkUiItem(
        "Apple", "AAPL", true
    ), onToggleFaved = { }, onToggleUnfaved = { })
}

@Preview(showBackground = true)
@Composable
fun NotFavedStonkListItemPreview() {
    SearchStonkListItem(state = SearchStonkUiItem(
        "Apple", "AAPL", false
    ), onToggleFaved = { }, onToggleUnfaved = { })
}