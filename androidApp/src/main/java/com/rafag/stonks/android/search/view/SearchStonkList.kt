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
    SearchStonkItemState("Apple", "AAPL", true),
    SearchStonkItemState("Apple", "AAPL", false),
)
