package com.rafag.stonks.android.faved.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rafag.stonks.android.design.theming.StonksText.BodyBigBold
import com.rafag.stonks.android.design.views.Delete
import com.rafag.stonks.android.design.views.Loading
import com.rafag.stonks.android.design.views.StonkQuote
import com.rafag.stonks.android.faved.R
import com.rafag.stonks.android.faved.presentation.FavedQuoteUi
import com.rafag.stonks.android.faved.presentation.FavedState
import com.rafag.stonks.android.faved.presentation.FavedState.*
import com.rafag.stonks.android.faved.presentation.FavedViewModel

@Composable
fun FavedQuotesScreen(
    viewModel: FavedViewModel,
    onNavigateToSearch: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect("load") {
        viewModel.load()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.my_stonks)) },
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 12.dp
            )
        }, content = {
            Box(modifier = Modifier.fillMaxSize()) {
                when (state) {
                    is Content -> Content(state as Content, viewModel::onDeleteStonkClicked)
                    Error -> Text("Error")
                    Loading -> Loading()
                }
                SearchButton {
                    onNavigateToSearch()
                }
            }
        })
}

@Composable
private fun BoxScope.SearchButton(onClick: () -> Unit) {
    FloatingActionButton(
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(16.dp),
        onClick = { onClick() },
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    ) {
        Icon(Icons.Filled.Search, "")
    }
}

@Composable
private fun Content(state: FavedState.Content, onDeleteClicked: (FavedQuoteUi) -> Unit) {
    LazyColumn {
        items(state.quotes) { item ->
            StonkItem(item, onDeleteClicked)
        }
    }
}

@Composable
private fun StonkItem(
    item: FavedQuoteUi,
    onDeleteStonkClicked: (FavedQuoteUi) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BodyBigBold(
            modifier = Modifier.weight(0.5f),
            text = item.symbol,
        )
        Box(
            modifier = Modifier
                .weight(0.35f)
                .padding(end = 8.dp)
        ) {
            StonkQuote(
                isUp = item.isUp,
                price = item.current,
                change = item.change
            )
        }
        Box(
            modifier = Modifier.weight(0.1f)
        ) {
            Delete(
                modifier = Modifier
                    .align(alignment = Center)
                    .clickable {
                        onDeleteStonkClicked(item)
                    }
            )
        }
    }
}
