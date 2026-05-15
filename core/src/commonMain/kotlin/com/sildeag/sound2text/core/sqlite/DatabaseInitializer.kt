package com.sildeag.sound2text.core.sqlite

interface DatabaseInitializer {
    fun createDriver(): SQLiteDriver
}
