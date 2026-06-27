package com.sildeag.sound2text.di

val appModule = module {
    // App state
    single { AppState() }
    // Recording controller
    single { RecordingController(get(), get(), get()) }
}