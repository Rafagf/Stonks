package com.rafag.stonks.android

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rafag.stonks.api.StonksHttpClient
import com.rafag.stonks.data.quote.QuoteModule
import com.rafag.stonks.db.DatabaseDriverFactory
import com.stonks.db.StonksDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : ComponentActivity(), CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val database = StonksDatabase(databaseDriverFactory.createDriver())
        setContent {
            foo()
        }
        launch {

            //do some sort of DI with all these dependencies
//            val httpClient = StonksHttpClient()
//            val driver = DatabaseDriverFactory(applicationContext).createDriver()
//            val db = StonksDatabase(driver)
//            val searchRepository = SearchModule(httpClient).repository()
//            val quoteRepository = QuoteModule(db = db).repository()
        }
    }
}
