package com.sildeag.sound2text.featuresqlite

import java.sql.ResultSet

class JdbcSQLiteCursor(
    private val rs: ResultSet
) : SQLiteCursor {
    override fun next(): Boolean = rs.next()
    override fun getString(index: Int): String? = rs.getString(index + 1)
    override fun getInt(index: Int): Int = rs.getInt(index + 1)
    override fun close() = rs.close()
}
