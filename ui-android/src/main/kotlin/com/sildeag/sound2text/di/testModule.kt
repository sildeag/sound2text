package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.model.note.NoteProvider
import com.sildeag.sound2text.service.note.NoteProviderImpl
import org.koin.dsl.module

val testModule = module {
    single<NoteProvider> { NoteProviderImpl(get(), get()) }
}
