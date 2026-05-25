package com.sildeag.sound2text.core.sqlite

expect class DatabaseFactory {
    fun createDriver(): SQLiteDriver
}
