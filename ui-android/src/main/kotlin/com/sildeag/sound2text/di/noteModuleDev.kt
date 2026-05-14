package com.sildeag.sound2text.di

import com.sildeag.sound2text.uiandroid.config.AppSettings
import com.sildeag.sound2text.uiandroid.config.Environment
import com.sildeag.sound2text.core.model.note.NoteProvider
import com.sildeag.sound2text.service.note.NoteProviderImpl
import org.koin.dsl.module

val noteModuleDev = module {
    single<AppSettings> { provideAppSettings(Environment.DEV) }
    single<NoteProvider> { NoteProviderImpl(get(), get()) }
}
