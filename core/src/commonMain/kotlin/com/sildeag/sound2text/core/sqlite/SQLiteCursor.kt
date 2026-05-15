package com.sildeag.sound2text.core.sqlite

interface SQLiteCursor {
    fun next(): Boolean
    fun getString(index: Int): String?
    fun getInt(index: Int): Int
    fun close()
}
