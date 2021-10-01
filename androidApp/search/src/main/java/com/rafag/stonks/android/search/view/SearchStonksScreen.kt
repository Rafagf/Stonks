package com.rafag.stonks.android.search.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.rafag.stonks.android.design.theming.StonksText
import com.rafag.stonks.android.design.theming.StonksText.BodyMedium
import com.rafag.stonks.android.design.views.Faved
import com.rafag.stonks.android.design.views.NotFaved
import com.rafag.stonks.android.design.views.SearchBar
import com.rafag.stonks.android.search.presentation.SearchState
import com.rafag.stonks.android.search.presentation.SearchState.*
import com.rafag.stonks.android.search.presentation.SearchStonkUi
import com.rafag.stonks.android.search.presentation.SearchViewModel

@Composable
fun SearchStonksScreen(viewModel: SearchViewModel) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val state by viewModel.state.collectAsState()

    Column {
        SearchBar(textState, viewModel::search)
        SearchStonkList(state, viewModel::onStonkFaved, viewModel::onStonkUnfaved)
    }
}

@Composable
fun SearchStonkList(
    state: SearchState,
    onToggleFaved: (SearchStonkUi) -> Unit,
    onToggleUnfaved: (SearchStonkUi) -> Unit
) {
    when (state) {
        is Content -> Content(state, onToggleFaved, onToggleUnfaved)
        Error -> Text("Error state")
        Loading -> Text("Loading state")
    }
}

@Composable
private fun Content(state: Content,
    onToggleFaved: (SearchStonkUi) -> Unit,
    onToggleUnfaved: (SearchStonkUi) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(state.searchStonks) { stonk ->
            ListItem(
                item = stonk,
                onToggleFaved = onToggleFaved,
                onToggleUnfaved = onToggleUnfaved,
            )
        }
    }
}

@Composable
private fun ListItem(
    item: SearchStonkUi,
    onToggleFaved: (SearchStonkUi) -> Unit,
    onToggleUnfaved: (SearchStonkUi) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = {
                if (item.faved) onToggleUnfaved(item) else onToggleFaved(item)
            })
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(16.dp)
        ) {
            StonksText.BodyMediumBold(text = item.name)
            BodyMedium(text = item.symbol)
        }
        if (item.faved) Faved() else NotFaved()
    }
}
