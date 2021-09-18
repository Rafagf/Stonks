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
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rafag.stonks.android.R
import com.rafag.stonks.android.faved.presentation.FavedQuoteUi
import com.rafag.stonks.android.faved.presentation.FavedState
import com.rafag.stonks.android.faved.presentation.FavedState.*
import com.rafag.stonks.android.faved.presentation.FavedViewModel
import com.rafag.stonks.android.navigation.NAVIGATE_TO_SEARCH_STONKS_SCREEN

@Composable
fun FavedQuotesScreen(
    navController: NavController,
    favedViewModel: FavedViewModel,
) {
    val state by favedViewModel.state.collectAsState()

    LaunchedEffect("load") {
        favedViewModel.load()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                backgroundColor = Color.Blue,
                contentColor = Color.White,
                elevation = 12.dp
            )
        }, content = {
            Box(modifier = Modifier.fillMaxSize()) {
                when (state) {
                    is Content -> content(state as Content) {

                    }
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
private fun content(state: FavedState.Content, onDeleteClicked: (FavedQuoteUi) -> Unit ) {
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
        Text(
            modifier = Modifier.weight(0.5f),
            text = item.symbol,
            fontSize = 18.sp,
            color = Color.Black
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
                    .background(if (item.isUp) Color.Green else Color.Red)
                    .padding(4.dp)
            ) {
                Text(
                    modifier = Modifier.align(CenterHorizontally),
                    text = item.current,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.align(CenterHorizontally),
                    text = item.change,
                    fontSize = 14.sp,
                    color = Color.Black
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
        tint = Color.DarkGray,
        modifier = modifier
            .size(24.dp)
    )
}
