package com.rafag.stonks.android.views

import androidx.compose.foundation.background
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
fun StonkListItem(stonk: StonkState, onItemClick: (StonkState) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(stonk) })
            .background(Color.Blue)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Box(modifier = Modifier.weight(0.3f)) { Text(text = stonk.name, fontSize = 18.sp, color = Color.White) }
        Box(modifier = Modifier.weight(0.1f)) { Text(text = stonk.price, fontSize = 18.sp, color = Color.White) }
        Box(modifier = Modifier.weight(0.1f)) { Text(text = stonk.change, fontSize = 18.sp, color = Color.White) }
    }
}

data class StonkState(
    val name: String,
    val price: String,
    val change: String
)

@Preview(showBackground = true)
@Composable
fun StonkListItemPreview() {
    StonkListItem(stonk = StonkState(
        "Apple", "234.34", "-0.32%"
    ), onItemClick = { })
}