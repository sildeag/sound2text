package com.sildeag.sound2text.di

import com.sildeag.sound2text.service.grammar.GrammarService
import com.sildeag.sound2text.service.grammar.MockGrammarService
import com.sildeag.sound2text.service.note.NoteEditorImpl
import com.sildeag.sound2text.service.note.NoteProvider
import com.sildeag.sound2text.service.note.NoteProviderImpl
import org.koin.dsl.module


val noteModuleProd = module {
    //single<AppSettings> { provideAppSettings(Environment.PROD) }
    single<NoteProvider> { NoteProviderImpl(get(), get()) }
    single<GrammarService> { MockGrammarService() }
    single { NoteEditorImpl() }
}
