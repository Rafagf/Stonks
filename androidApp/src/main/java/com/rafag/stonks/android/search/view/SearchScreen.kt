package com.rafag.stonks.android.search.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.rafag.stonks.android.search.presentation.SearchStonkUi
import com.rafag.stonks.android.search.presentation.SearchViewModel

interface SearchScreenActions {

    fun onSearchQueryChanged(query: String)
    fun onStonkFaved(item: SearchStonkUi)
    fun onStonkUnfaved(item: SearchStonkUi)
}

@Composable
fun SearchScreen(searchViewModel: SearchViewModel, actions: SearchScreenActions) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val state by searchViewModel.state.collectAsState()

    Column {
        SearchBar(textState, actions::onSearchQueryChanged)
        SearchStonkList(state, actions)
    }
}

