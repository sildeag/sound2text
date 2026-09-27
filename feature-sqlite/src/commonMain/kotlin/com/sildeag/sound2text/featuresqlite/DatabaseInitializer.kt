package com.sildeag.sound2text.featuresqlite

interface DatabaseInitializer {
    fun createDriver(): SQLiteDriver
}
