package com.sildeag.sound2text.core.sqlite

import java.sql.Connection
import java.sql.DriverManager
class JdbcSQLiteDriver(path: String) : SQLiteDriver {
    private val conn: Connection =
        DriverManager.getConnection("jdbc:sqlite:$path")
    override fun execute(sql: String, args: List<Any?>) {
        conn.prepareStatement(sql).use { stmt ->
            args.forEachIndexed { i, arg -> stmt.setObject(i + 1,
                arg) }
            stmt.execute()
        }
    }
    override fun executeQuery(sql: String, args: List<Any?>):
            SQLiteCursor {
        val stmt = conn.prepareStatement(sql)
        args.forEachIndexed { i, arg -> stmt.setObject(i + 1, arg) }
        val rs = stmt.executeQuery()
        return JdbcSQLiteCursor(rs)
    }
}
