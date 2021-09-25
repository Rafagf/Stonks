package com.rafag.stonks.android.faved.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rafag.stonks.android.R
import com.rafag.stonks.android.faved.presentation.FavedQuoteUi
import com.rafag.stonks.android.faved.presentation.FavedState
import com.rafag.stonks.android.faved.presentation.FavedState.*
import com.rafag.stonks.android.faved.presentation.FavedViewModel
import com.rafag.stonks.android.navigation.NAVIGATE_TO_SEARCH_STONKS_SCREEN
import com.rafag.stonks.android.theming.StonksColors
import com.rafag.stonks.android.theming.StonksText
import com.rafag.stonks.android.theming.StonksText.BodyBigBold

@Composable
fun FavedQuotesScreen(
    navController: NavController,
    viewModel: FavedViewModel,
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
                    is Content -> content(state as Content, viewModel::onDeleteStonkClicked)
                    Error -> Text("Error")
                    Loading -> Text("Loading")
                }
                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                    onClick = {
                        navController.navigate(NAVIGATE_TO_SEARCH_STONKS_SCREEN)
                    },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                ) {
                    Icon(Icons.Filled.Search, "")
                }
            }
        })
}

@Composable
private fun content(state: FavedState.Content, onDeleteClicked: (FavedQuoteUi) -> Unit) {
    LazyColumn {
        items(state.quotes) { item ->
            Item(item, onDeleteClicked)
        }
    }
}

@Composable
private fun Item(
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (item.isUp) StonksColors.green400 else StonksColors.red400)
                    .padding(4.dp)
            ) {
                StonksText.BodyMediumBold(
                    modifier = Modifier.align(CenterHorizontally),
                    color = MaterialTheme.colors.quotesTextColor,
                    text = item.current,
                )
                StonksText.BodySmall(
                    modifier = Modifier.align(CenterHorizontally),
                    color = MaterialTheme.colors.quotesTextColor,
                    text = item.change,
                )
            }
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

@Composable
private fun Delete(modifier: Modifier) {
    Icon(
        Filled.Delete,
        contentDescription = "",
        tint = StonksColors.gray600,
        modifier = modifier
            .size(24.dp)
    )
}

private val Colors.quotesTextColor: Color
    @Composable get() = StonksColors.white