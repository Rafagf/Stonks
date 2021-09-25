package com.rafag.stonks.android.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

const val NAVIGATE_TO_FAVED_QUOTES_SCREEN = "faved_quotes_screen"
const val NAVIGATE_TO_SEARCH_STONKS_SCREEN = "search_stonks_screen"

//TODO cannot be used yet, can't access the screens. We need to extract the features to their feature modules first

//@Composable
//fun ComposeNavigation(navController: NavHostController) {
//    val application = (LocalContext.current.applicationContext as Application)
//    NavHost(
//        navController = navController,
//        startDestination = NAVIGATE_TO_FAVED_QUOTES_SCREEN
//    ) {
//        composable(NAVIGATE_TO_FAVED_QUOTES_SCREEN) {
////            FavedQuotesScreen(navController = navController, viewModel = application.favedQuotesViewModel())
//        }
//        composable(NAVIGATE_TO_SEARCH_STONKS_SCREEN) {
////            SearchStonksScreen(viewModel = application.searchStonksViewModel())
//        }
//    }
//}