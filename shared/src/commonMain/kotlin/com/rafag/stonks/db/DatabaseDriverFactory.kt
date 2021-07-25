package com.rafag.stonks.db

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {

    fun createDriver(): SqlDriver
}