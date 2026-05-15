package com.sildeag.sound2text.core.sqlite

import java.io.File
import java.sql.Connection
import java.sql.DriverManager
private const val DB_NAME = "sound2text.db"
class DesktopDatabaseInitializer(
    private val baseDir: File
) : DatabaseInitializer {
    override fun createDriver(): SQLiteDriver {
        val path = File(baseDir, DB_NAME).absolutePath
        val conn: Connection = DriverManager.getConnection("jdbc:sqlite:$path")
        createSchema(conn)
        return JdbcSQLiteDriver(path)
    }
}
private fun createSchema(conn: Connection) {
    conn.createStatement().use { stmt ->
        stmt.execute(
            """
 CREATE TABLE IF NOT EXISTS notes (
 id TEXT PRIMARY KEY,
 title TEXT NOT NULL
 )
 """.trimIndent()
        )
        stmt.execute(
            """
 CREATE TABLE IF NOT EXISTS note_fields (
 id TEXT PRIMARY KEY,
 note_id TEXT NOT NULL,
 type TEXT NOT NULL,
 level INTEGER NOT NULL,
 anchor TEXT,
 language TEXT,
 payload TEXT NOT NULL,
 FOREIGN KEY(note_id) REFERENCES notes(id) ON DELETE CASCADE
 )
 """.trimIndent()
        )
    }
}