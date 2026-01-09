package com.sildeag.sound2text.di

import org.koin.dsl.module

object NoteEditorMockModule {
    val module = module(override = true) {
        single<NoteEditor> { MockNoteEditor() }
    }
}
