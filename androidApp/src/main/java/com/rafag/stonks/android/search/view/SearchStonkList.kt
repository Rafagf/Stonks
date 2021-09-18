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


