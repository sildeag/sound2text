package com.sildeag.sound2text.di

import com.sildeag.sound2text.service.note.NoteEditor
import org.koin.dsl.module

object NoteEditorMockModule {
    val module = module(override = true) {
        single<NoteEditor> { MockNoteEditor() }
    }
}
