package com.sildeag.sound2text.di.sqlite

import com.sildeag.sound2text.core.sqlite.DatabaseFactory
import org.koin.dsl.module

val androidDatabaseModule = module {
    single<DatabaseFactory> { DatabaseFactory(androidContext()) }
}