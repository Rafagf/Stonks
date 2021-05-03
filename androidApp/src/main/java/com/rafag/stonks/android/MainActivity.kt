package com.rafag.stonks.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rafag.stonks.Greeting
import android.widget.TextView
import com.rafag.stonks.Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity(), CoroutineScope {

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

        launch {
            val tv: TextView = findViewById(R.id.text_view)
            tv.text = greet() + Api().testQuery()
        }
    }
}
