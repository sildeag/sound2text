package com.sildeag.sound2text.di

import com.sildeag.sound2text.pdf.*
import org.koin.dsl.module
fun harnessModule(config: Config) = module {
    single { config }
    single { AppEnvironment(get()) }
    single { AppUiMode(get()) }
}