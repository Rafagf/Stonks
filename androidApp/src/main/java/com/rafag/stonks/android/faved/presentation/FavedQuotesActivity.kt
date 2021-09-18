package com.rafag.stonks.android.faved.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rafag.stonks.android.di.favedViewModel
import com.rafag.stonks.android.faved.view.FavedQuotesScreen
import com.rafag.stonks.android.faved.view.FavedQuotesScreenActions

class FavedQuotesActivity : ComponentActivity() {

    private lateinit var favedViewModel: FavedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favedViewModel = favedViewModel()
        setContent {
            FavedQuotesScreen(favedViewModel, actions = object : FavedQuotesScreenActions {
                override fun onDeleteStonkClicked(item: FavedQuoteUi) {
                    favedViewModel.onDeleteStonkClicked(item)
                }
            })
        }
    }
}
