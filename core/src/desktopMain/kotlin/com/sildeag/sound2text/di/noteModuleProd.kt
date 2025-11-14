package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.core.config.provideAppSettings
import com.sildeag.sound2text.core.service.grammar.GrammarService
import com.sildeag.sound2text.core.service.grammar.MockGrammarService
import com.sildeag.sound2text.core.service.note.NoteEditor
import com.sildeag.sound2text.core.service.note.NoteEditorImpl
import com.sildeag.sound2text.core.service.note.NoteProvider
import com.sildeag.sound2text.core.service.note.NoteProviderImpl
import org.koin.dsl.module

val noteModuleProd = module {
    //single<AppSettings> { provideAppSettings(Environment.PROD) }
    single<NoteProvider> { NoteProviderImpl(get(), get()) }
    single<GrammarService> { MockGrammarService() }
    single<NoteEditor> { NoteEditorImpl() }
}
