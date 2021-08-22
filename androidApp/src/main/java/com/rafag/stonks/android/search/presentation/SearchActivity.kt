package com.rafag.stonks.android.search.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rafag.stonks.android.search.view.Actions
import com.rafag.stonks.android.search.view.SearchScreen
import com.rafag.stonks.android.searchRepository

class SearchActivity : ComponentActivity() {

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
