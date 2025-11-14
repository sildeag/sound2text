package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.core.config.provideAppSettings
import com.sildeag.sound2text.core.service.note.NoteProvider
import com.sildeag.sound2text.core.service.note.NoteProviderImpl
import org.koin.dsl.module

val noteModuleDev = module {
    single<AppSettings> { provideAppSettings(Environment.DEV) }
    single<NoteProvider> { NoteProviderImpl(get(), get()) }
}
