package com.rafag.stonks.android.faved.view

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rafag.stonks.android.R
import com.rafag.stonks.android.faved.presentation.FavedState
import com.rafag.stonks.android.faved.presentation.FavedState.*
import com.rafag.stonks.android.faved.presentation.FavedViewModel
import com.rafag.stonks.android.search.presentation.SearchActivity

@Composable
fun FavedScreen(favedViewModel: FavedViewModel) {
    val context = LocalContext.current
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
                    is Content -> content(state as Content)
                    Error -> Text("Error")
                    Loading -> Text("Loading")
                }
                FloatingActionButton(
                    modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                    onClick = {
                        //todo use compose navigation
                        val intent = Intent(context, SearchActivity::class.java, )
                        context.startActivity(intent)
                    },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                ) {
                    Icon(Icons.Filled.Search, "")
                }
            }
        })
}

@Composable
private fun content(state: FavedState.Content) {
    LazyColumn {
        items(state.quotes) { stonk ->
            Text("${stonk.symbol} ${stonk.open} ${stonk.current} ${stonk.percentageChange}")
        }
    }
}
