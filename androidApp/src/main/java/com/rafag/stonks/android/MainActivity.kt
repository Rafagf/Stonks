package com.rafag.stonks.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.rafag.stonks.android.views.Actions
import com.rafag.stonks.android.views.SearchScreen

class MainActivity : ComponentActivity() {

    private val vm: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //TODO to be moved into a different screen or similar, living under a floating search button
            SearchScreen(vm, object : Actions {
                override fun onSearchQueryChanged(query: String) {
                    vm.search(query)
                }
            })
        }
//        launch {

        //do some sort of DI with all these dependencies
//            val httpClient = StonksHttpClient()
        //        val database = StonksDatabase(databaseDriverFactory.createDriver())
//            val driver = DatabaseDriverFactory(applicationContext).createDriver()
//            val db = StonksDatabase(driver)
//            val searchRepository = SearchModule(httpClient).repository()
//            val quoteRepository = QuoteModule(db = db).repository()
//        }
    }
}
