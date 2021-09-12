package com.rafag.stonks.android.faved.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rafag.stonks.android.di.favedViewModel
import com.rafag.stonks.android.faved.view.FavedScreen

class FavedActivity : ComponentActivity() {

    private lateinit var favedViewModel: FavedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favedViewModel = favedViewModel()
        setContent {
            FavedScreen(favedViewModel)
        }
    }
}
