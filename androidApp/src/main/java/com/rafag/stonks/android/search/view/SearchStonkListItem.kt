package com.rafag.stonks.android.search.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchStonkListItem(state: SearchStonkItemState, onToggleFaved: (SearchStonkItemState) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onToggleFaved(state) })
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Box(modifier = Modifier.weight(0.3f)) { Text(text = state.name, fontSize = 18.sp, color = Color.Black) }
        Box(modifier = Modifier.weight(0.1f)) { Text(text = state.price, fontSize = 18.sp, color = Color.Black) }
        Box(modifier = Modifier.weight(0.1f)) { Text(text = state.change, fontSize = 18.sp, color = Color.Black) }
    }
}

data class SearchStonkItemState(
    val name: String,
    val symbol: String,
    val faved: Boolean,
)

@Preview(showBackground = true)
@Composable
fun StonkListItemPreview() {
    SearchStonkListItem(state = SearchStonkItemState(
        "Apple", "AAPL", true
    ), onToggleFaved = { })
}