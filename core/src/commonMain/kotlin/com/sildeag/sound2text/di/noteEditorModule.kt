package com.sildeag.sound2text.di

import com.sildeag.sound2text.service.note.NoteEditor
import com.sildeag.sound2text.service.note.NoteEditorImpl
import org.koin.dsl.module

val noteEditorModule = module {
    single<NoteEditor> { NoteEditorImpl() } // or whatever implementation you're using
}