package com.rafag.stonks.android.search.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rafag.stonks.android.di.searchViewModel
import com.rafag.stonks.android.search.view.SearchScreen
import com.rafag.stonks.android.search.view.SearchScreenActions
import com.rafag.stonks.android.search.view.SearchStonkItemState

class SearchActivity : ComponentActivity() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel = searchViewModel()
        setContent {
            SearchScreen(searchViewModel, object : SearchScreenActions {
                override fun onSearchQueryChanged(query: String) {
                    searchViewModel.search(query)
                }

                override fun onStonkFaved(item: SearchStonkItemState) {
                    searchViewModel.onStonkFaved(item)
                }

                override fun onStonkUnfaved(item: SearchStonkItemState) {
                    searchViewModel.onStonkUnfaved(item)
                }
            })
        }
    }
}
