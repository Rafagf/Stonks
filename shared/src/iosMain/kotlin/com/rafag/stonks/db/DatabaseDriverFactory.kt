package com.rafag.stonks.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.stonks.db.StonksDatabase

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(StonksDatabase.Schema, "test.db")
    }
}