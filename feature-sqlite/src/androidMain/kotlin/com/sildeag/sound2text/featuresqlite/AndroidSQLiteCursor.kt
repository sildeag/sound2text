package com.sildeag.sound2text.featuresqlite

import android.database.Cursor

class AndroidSQLiteCursor(
    private val cursor: Cursor
) : SQLiteCursor {
    override fun next(): Boolean = cursor.moveToNext()
    override fun getString(index: Int): String? = cursor.getString(index)
    override fun getInt(index: Int): Int = cursor.getInt(index)
    override fun close() = cursor.close()
}
