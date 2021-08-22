package com.rafag.stonks.android.search.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.rafag.stonks.android.search.presentation.SearchViewModel

@Composable
fun SearchScreen(searchViewModel: SearchViewModel, actions: Actions) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column {
        SearchStonkList(textState, actions)
        searchViewModel.state.observeAsState().value?.let { SearchStonkList(it) }
    }
}