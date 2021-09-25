package com.rafag.stonks.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.rafag.stonks.android.navigation.ComposeNavigation
import com.rafag.stonks.android.theming.StonksTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StonksTheme {
                val navController = rememberNavController()
                Scaffold {
                    ComposeNavigation(navController)
                }
            }
        }
    }
}
