package com.sildeag.sound2text.uidesktop.di

val uiDesktopModule = module {
    single { MainViewModel(get(), get()) }
    single { PdfViewModel(get(), get()) }
}