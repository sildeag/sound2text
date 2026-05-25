package com.sildeag.sound2text.di

import com.sildeag.sound2text.pdfdesktop.*
import org.koin.dsl.module
fun harnessModule(config: Config) = module {
    single { config }
    single { AppEnvironment(get()) }
    single { AppUiMode(get()) }
}
