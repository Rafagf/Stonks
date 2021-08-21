package com.rafag.stonks.android.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rafag.stonks.android.SearchState

@Composable
fun SearchView(state: SearchState) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(state.stonks) { stonk ->
            StonkListItem(
                item = stonk,
                onItemClick = { selectedStonk ->
                    //todo
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StonkListPreview() {
    SearchView(SearchState(dummyData))
}

val dummyData = listOf(
    StonkItemState("Apple", "234", "4%"),
    StonkItemState("Amazon", "234", "4%"),
    StonkItemState("Google", "234", "4%"),
    StonkItemState("Googla", "234", "4%"),
    StonkItemState("Testla", "234", "4%"),
    StonkItemState("Testlapu", "234", "4%"),
    StonkItemState("Tiolo", "234", "4%"),
    StonkItemState("Alibabo", "234", "4%"),
)
