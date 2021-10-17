package com.rafag.stonks.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.rafag.stonks.android.design.TintStatusBar
import com.rafag.stonks.android.design.theming.StonksTheme
import com.rafag.stonks.android.di.favedViewModel
import com.rafag.stonks.android.di.searchViewModel
import com.rafag.stonks.android.navigation.ComposeNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StonksTheme {
                TintStatusBar()
                val navController = rememberNavController()
                val application = application as StonksApplication
                Scaffold {
                    ComposeNavigation(
                        navController = navController,
                        searchViewModel = application.searchViewModel(),
                        favedQuotesViewModel = application.favedViewModel()
                    )
                }
            }
        }
    }
}
