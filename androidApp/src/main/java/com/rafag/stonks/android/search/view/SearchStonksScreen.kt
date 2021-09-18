package com.rafag.stonks.android.search.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
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

