package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.model.note.NoteProvider
import com.sildeag.sound2text.service.note.NoteProviderImpl
import org.koin.dsl.module

val devModule = module {
    single<NoteProvider> { NoteProviderImpl(get(), get()) }
}
