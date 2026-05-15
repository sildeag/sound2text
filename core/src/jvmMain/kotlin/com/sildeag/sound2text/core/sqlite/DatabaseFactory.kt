package com.sildeag.sound2text.core.sqlite

import java.io.File
actual class DatabaseFactory(
    private val baseDir: File
) {
    actual fun createDriver(): SQLiteDriver {
        return DesktopDatabaseInitializer(baseDir).createDriver()
    }
}