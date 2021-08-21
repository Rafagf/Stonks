package com.rafag.stonks.android.views

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
fun StonkListItem(item: StonkItemState, onItemClick: (StonkItemState) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(item) })
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Box(modifier = Modifier.weight(0.3f)) { Text(text = item.name, fontSize = 18.sp, color = Color.Black) }
        Box(modifier = Modifier.weight(0.1f)) { Text(text = item.price, fontSize = 18.sp, color = Color.Black) }
        Box(modifier = Modifier.weight(0.1f)) { Text(text = item.change, fontSize = 18.sp, color = Color.Black) }
    }
}

data class StonkItemState(
    val name: String,
    val price: String,
    val change: String
)

@Preview(showBackground = true)
@Composable
fun StonkListItemPreview() {
    StonkListItem(item = StonkItemState(
        "Apple", "234.34", "-0.32%"
    ), onItemClick = { })
}