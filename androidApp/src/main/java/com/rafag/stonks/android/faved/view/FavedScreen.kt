package com.rafag.stonks.android.faved.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExtendedFloatingActionButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rafag.stonks.android.R
import com.rafag.stonks.android.faved.presentation.FavedViewModel
import com.rafag.stonks.android.faved.view.FavedState.*

@Composable
fun FavedScreen(favedViewModel: FavedViewModel) {
    val state by favedViewModel.stateFlow.collectAsState()

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
                    Errror -> Text("Error")
                    Loading -> Text("Loading")
                }
                FloatingActionButton(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    onClick = {
                        //todo just deving to see if data flow works correctly: it does!
                        favedViewModel.foo()
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
        items(state.strings) { string ->
            Text(string)
        }
    }
}

sealed class FavedState {
    object Loading : FavedState()
    object Errror : FavedState()
    data class Content(val strings: List<String>) : FavedState()
}