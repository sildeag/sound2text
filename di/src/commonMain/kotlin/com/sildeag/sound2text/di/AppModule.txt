package com.sildeag.sound2text.di

import org.koin.dsl.module

val appModule = module {
    // App state
    single { AppState() }
    // Recording controller
    single { RecordingController(get(), get(), get()) }
}