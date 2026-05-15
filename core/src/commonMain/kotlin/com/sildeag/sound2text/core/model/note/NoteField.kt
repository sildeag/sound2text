package com.sildeag.sound2text.core.model.note

sealed interface NoteField {
    val id: String
    val anchor: String?
    val level: Int
    val language: String?
}