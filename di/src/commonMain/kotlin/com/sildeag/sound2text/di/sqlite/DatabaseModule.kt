package com.sildeag.sound2text.di.sqlite

import com.sildeag.sound2text.core.sqlite.DatabaseFactory
import com.sildeag.sound2text.core.sqlite.SQLiteDriver
import org.koin.dsl.module

fun databaseModule(factory: DatabaseFactory) = module {
    single<SQLiteDriver> { factory.createDriver() }
}