package com.sildeag.sound2text.core.sqlite

import android.content.Context
actual class DatabaseFactory(
    private val context: Context
) {
    actual fun createDriver(): SQLiteDriver {
        return AndroidDatabaseInitializer(context).createDriver()
    }
}
