package com.rafag.stonks.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rafag.stonks.android.views.Actions
import com.rafag.stonks.android.views.SearchScreen

class MainActivity : ComponentActivity() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel = SearchViewModel(searchRepository())
        setContent {
            SearchScreen(searchViewModel, object : Actions {
                override fun onSearchQueryChanged(query: String) {
                    searchViewModel.search(query)
                }
            })
        }
    }
}
