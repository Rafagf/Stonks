package com.rafag.stonks.android.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StonkList(stonks: List<StonkState>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(stonks) { stonk ->
            StonkListItem(
                stonk = stonk,
                onItemClick = { selectedStonk ->
                    /* Add code later */
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StonkListPreview() {
    StonkList(dummyData)
}

val dummyData = listOf(
    StonkState("Apple", "234", "4%"),
    StonkState("Amazon", "234", "4%"),
    StonkState("Google", "234", "4%"),
    StonkState("Googla", "234", "4%"),
    StonkState("Testla", "234", "4%"),
    StonkState("Testlapu", "234", "4%"),
    StonkState("Tiolo", "234", "4%"),
    StonkState("Alibabo", "234", "4%"),
)
