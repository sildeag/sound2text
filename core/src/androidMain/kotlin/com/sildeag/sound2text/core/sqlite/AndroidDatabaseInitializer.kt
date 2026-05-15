package com.sildeag.sound2text.core.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.sqlite.driver.AndroidSQLiteDriver

private const val DB_NAME = "sound2text.db"
private const val DB_VERSION = 1
class AndroidDatabaseInitializer(
    private val context: Context
) : DatabaseInitializer {
    override fun createDriver(): SQLiteDriver {
        val helper = object : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
            override fun onCreate(db: SQLiteDatabase) {
                createSchema(db)
            }
            override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
                migrate(db, oldVersion, newVersion)
            }
        }
        return AndroidSQLiteDriver(helper.writableDatabase)
    }
}

private fun createSchema(db: SQLiteDatabase) {
    db.execSQL(
        """
 CREATE TABLE IF NOT EXISTS notes (
 id TEXT PRIMARY KEY,
 title TEXT NOT NULL
 )
 """.trimIndent()
    )
    db.execSQL(
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
private fun migrate(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    // v1: initial schema, nothing to migrate yet
}
