package com.rafag.stonks

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ApiWrapper {
    fun Api.request(completion: (String) -> Unit) {
        val api = this
        MainScope().launch {
            val result = api.testQuery()
            completion(result)
        }
    }
}