package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.service.note.NoteProvider
import com.sildeag.sound2text.core.service.note.NoteProviderImpl
import org.koin.dsl.module

val testModule = module {
    single<NoteProvider> { NoteProviderImpl(get(), get()) }
}
