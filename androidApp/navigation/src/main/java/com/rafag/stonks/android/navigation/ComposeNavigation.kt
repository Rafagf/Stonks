package com.rafag.stonks.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rafag.stonks.android.faved.presentation.FavedViewModel
import com.rafag.stonks.android.faved.view.FavedQuotesScreen
import com.rafag.stonks.android.search.presentation.SearchViewModel
import com.rafag.stonks.android.search.view.SearchStonksScreen

const val NAVIGATE_TO_FAVED_QUOTES_SCREEN = "faved_quotes_screen"
const val NAVIGATE_TO_SEARCH_STONKS_SCREEN = "search_stonks_screen"

@Composable
fun ComposeNavigation(
    navController: NavHostController,
    searchViewModel: SearchViewModel,
    favedQuotesViewModel: FavedViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = NAVIGATE_TO_FAVED_QUOTES_SCREEN
    ) {
        composable(NAVIGATE_TO_FAVED_QUOTES_SCREEN) {
            FavedQuotesScreen(navController = navController, viewModel = favedQuotesViewModel)
        }
        composable(NAVIGATE_TO_SEARCH_STONKS_SCREEN) {
            SearchStonksScreen(viewModel = searchViewModel)
        }
    }
}
