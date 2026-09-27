package com.sildeag.sound2text.featuresqlite

interface SQLiteDriver {
    fun execute(sql: String, args: List<Any?> = emptyList())
    fun executeQuery(sql: String, args: List<Any?> = emptyList()): SQLiteCursor
}
