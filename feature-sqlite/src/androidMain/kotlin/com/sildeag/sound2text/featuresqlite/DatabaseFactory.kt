package com.sildeag.sound2text.featuresqlite

import android.content.Context
import com.sildeag.sound2text.featuresqlite.SQLiteDriver

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun createDriver(): SQLiteDriver {
        return AndroidDatabaseInitializer(context).createDriver()
    }
}