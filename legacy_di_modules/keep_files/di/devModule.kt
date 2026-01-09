package com.sildeag.sound2text.di

import org.koin.dsl.module
import com.sildeag.sound2text.service.note.NoteProvider
import com.sildeag.sound2text.service.note.NoteProviderImpl

val devModule = module {
    single<NoteProvider> {
        NoteProviderImpl(get(),get()) }
    // No PulseLogic here ✅
}