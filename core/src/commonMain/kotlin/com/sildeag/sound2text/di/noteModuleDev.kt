package com.sildeag.sound2text.di

import com.sildeag.sound2text.config.AppSettings
import com.sildeag.sound2text.config.Environment
import com.sildeag.sound2text.service.note.NoteProvider
import com.sildeag.sound2text.service.note.NoteProviderImpl
import org.koin.dsl.module

val noteModuleDev = module {
    single<AppSettings> { provideAppSettings(Environment.DEV) }
    println("Registering NoteProvider with PdfContentImpl")
    single<NoteProvider> { NoteProviderImpl(get(), get()) }
}


