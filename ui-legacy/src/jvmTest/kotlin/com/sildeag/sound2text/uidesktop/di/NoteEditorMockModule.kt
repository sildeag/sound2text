package com.sildeag.sound2text.uidesktop.di

import org.koin.dsl.module

object NoteEditorMockModule {
    val module = module(override = true) {
        single<NoteEditor> { MockNoteEditor() }
    }
}
