package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.model.note.NoteEditor
import com.sildeag.sound2text.service.note.NoteEditorImpl
import org.koin.dsl.module

val noteEditorModule = module {
    single<NoteEditor> { NoteEditorImpl() }
}
