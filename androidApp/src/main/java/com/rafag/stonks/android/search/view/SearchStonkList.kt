package com.rafag.stonks.android.search.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafag.stonks.android.search.presentation.SearchState
import com.rafag.stonks.android.search.presentation.SearchState.*
import com.rafag.stonks.android.search.presentation.SearchStonkUi

@Composable
fun SearchStonkList(state: SearchState, actions: SearchScreenActions) {
    when (state) {
        is Content -> Content(state, actions)
        Error -> Text("Error state")
        Loading -> Text("Loading state")
    }
}

@Composable
private fun Content(state: Content, actions: SearchScreenActions) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(state.searchStonks) { stonk ->
            ListItem(
                item = stonk,
                onToggleFaved = actions::onStonkFaved,
                onToggleUnfaved = actions::onStonkUnfaved,
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
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(0.8f)
                .padding(16.dp)
        ) {
            Text(text = item.name, fontSize = 18.sp, color = Color.Black)
            Text(text = item.symbol, fontSize = 14.sp, color = Color.Black)
        }
        if (item.faved) Faved() else NotFaved()

    }
}

@Composable
private fun Faved() {
    Icon(
        Filled.Favorite,
        contentDescription = "",
        tint = Color.Red,
        modifier = Modifier
            .padding(15.dp)
            .size(24.dp)
    )
}

@Composable
private fun NotFaved() {
    Icon(
        Filled.Favorite,
        contentDescription = "",
        tint = Color.LightGray,
        modifier = Modifier
            .padding(16.dp)
            .size(24.dp)
    )
}




