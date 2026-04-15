package com.sildeag.sound2text.di

import com.sildeag.sound2text.uiandroid.config.AppSettings
import com.sildeag.sound2text.uiandroid.config.Environment
import com.sildeag.sound2text.service.note.NoteProvider
import com.sildeag.sound2text.service.note.NoteProviderImpl
import org.koin.dsl.module

val noteModuleTest = module {
    single<AppSettings> { provideAppSettings(Environment.TEST) }
    single<NoteProvider> { NoteProviderImpl(get(), get()) }
}