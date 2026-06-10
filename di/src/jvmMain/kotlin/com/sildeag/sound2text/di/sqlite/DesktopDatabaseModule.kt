package com.sildeag.sound2text.di.sqlite

import com.sildeag.sound2text.core.sqlite.DatabaseFactory
import org.koin.dsl.module

val desktopDatabaseModule = module {
    single<DatabaseFactory> { DatabaseFactory(get()) } // baseDir File
}