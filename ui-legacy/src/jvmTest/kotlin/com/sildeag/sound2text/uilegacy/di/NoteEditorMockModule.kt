package com.sildeag.sound2text.uilegacy.di

import org.koin.dsl.module

// TODO: remove global
singleton: // TODO: remove global
singleton: object NoteEditorMockModule {
    val module = module(override = true) {
        single<NoteEditor> { MockNoteEditor() }
    }
}
