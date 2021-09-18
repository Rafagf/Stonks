package com.rafag.stonks.android.search.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rafag.stonks.android.search.presentation.SearchState
import com.rafag.stonks.android.search.presentation.SearchState.*

@Composable
fun SearchStonkList(state: SearchState, actions: SearchScreenActions) {
    when (state) {
        is Content -> {
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
        Error -> Text("Error state")
        Loading -> Text("Loading state")
    }
}


@Preview(showBackground = true)
@Composable
fun StonkListPreview() {
    SearchStonkList(SearchState.Loading, object : SearchScreenActions {
        override fun onSearchQueryChanged(query: String) {
            //no op
        }

        override fun onStonkFaved(item: SearchStonkUiItem) {
            //no op
        }

        override fun onStonkUnfaved(item: SearchStonkUiItem) {
            //no op
        }
    })
}

private val dummyData = listOf(
    SearchStonkUiItem("Apple", "AAPL", true),
    SearchStonkUiItem("Apple", "AAPL", false),
)
