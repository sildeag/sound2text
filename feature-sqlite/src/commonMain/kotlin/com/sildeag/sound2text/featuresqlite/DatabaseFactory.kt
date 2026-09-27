package com.sildeag.sound2text.featuresqlite

expect class DatabaseFactory {
    fun createDriver(): SQLiteDriver
}
