package com.sildeag.sound2text.core.sqlite

import android.database.sqlite.SQLiteDatabase
class AndroidSQLiteDriver(
    private val db: SQLiteDatabase
) : SQLiteDriver {
    override fun execute(sql: String, args: List<Any?>) {
        db.execSQL(sql, args.toTypedArray())
    }
    override fun executeQuery(sql: String, args: List<Any?>): SQLiteCursor {
        val cursor = db.rawQuery(sql, args.map { it.toString() }.toTypedArray())
        return AndroidSQLiteCursor(cursor)
    }
}