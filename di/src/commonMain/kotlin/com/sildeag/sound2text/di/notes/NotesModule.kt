package com.sildeag.sound2text.di.notes

import com.sildeag.sound2text.core.notes.NoteRepository
import com.sildeag.sound2text.core.notes.NoteRepositorySQLite
import com.sildeag.sound2text.core.sqlite.SQLiteDriver
import org.koin.dsl.module

val notesModule = module {
    // SQLite driver (platform)
    single<SQLiteDriver> { getPlatformSQLiteDriver() }
    // Repository
    single<NoteRepository> { NoteRepositorySQLite(get()) }
}