package com.sildeag.sound2text.core.di

import com.sildeag.sound2text.core.service.note.NoteEditor
import com.sildeag.sound2text.core.service.note.NoteEditorImpl
import org.koin.dsl.module

val noteEditorModule = module {
    single<NoteEditor> { NoteEditorImpl() }
}