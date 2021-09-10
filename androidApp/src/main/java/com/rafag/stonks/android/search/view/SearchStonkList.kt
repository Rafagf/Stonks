package com.rafag.stonks.android.search.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rafag.stonks.android.search.presentation.SearchState

@Composable
fun SearchStonkList(state: SearchState, actions: SearchScreenActions) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(state.searchStonks) { stonk ->
            SearchStonkListItem(
                state = stonk,
                onToggleFaved = actions::onStonkFaved,
                onToggleUnfaved = actions::onStonkUnfaved,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StonkListPreview() {
    SearchStonkList(SearchState(dummyData), object : SearchScreenActions {
        override fun onSearchQueryChanged(query: String) {
            //no op
        }

        override fun onStonkFaved(item: SearchStonkItemState) {
            //no op
        }

        override fun onStonkUnfaved(item: SearchStonkItemState) {
            //no op
        }
    })
}

private val dummyData = listOf(
    SearchStonkItemState("Apple", "AAPL", true),
    SearchStonkItemState("Apple", "AAPL", false),
)
