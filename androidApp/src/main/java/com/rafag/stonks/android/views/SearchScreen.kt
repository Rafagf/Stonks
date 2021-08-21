package com.rafag.stonks.android.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.rafag.stonks.android.SearchViewModel

@Composable
fun SearchScreen(searchViewModel: SearchViewModel, actions: Actions) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column {
        SearchView(textState, actions)
        searchViewModel.state.observeAsState().value?.let { SearchView(it) }
    }
}