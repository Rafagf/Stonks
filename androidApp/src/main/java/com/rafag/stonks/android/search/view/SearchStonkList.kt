package com.rafag.stonks.android.search.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rafag.stonks.android.search.presentation.SearchState

@Composable
fun SearchStonkList(state: SearchState) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(state.searchStonks) { stonk ->
            SearchStonkListItem(
                state = stonk,
                onToggleFaved = { selectedStonk ->
                    //todo
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StonkListPreview() {
    SearchStonkList(SearchState(dummyData))
}

private val dummyData = listOf(
    SearchStonkItemState("Apple", "234", "4%"),
    SearchStonkItemState("Amazon", "234", "4%"),
    SearchStonkItemState("Google", "234", "4%"),
    SearchStonkItemState("Googla", "234", "4%"),
    SearchStonkItemState("Testla", "234", "4%"),
    SearchStonkItemState("Testlapu", "234", "4%"),
    SearchStonkItemState("Tiolo", "234", "4%"),
    SearchStonkItemState("Alibabo", "234", "4%"),
)
