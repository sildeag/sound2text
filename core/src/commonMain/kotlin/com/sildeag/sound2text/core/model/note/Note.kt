package com.sildeag.sound2text.core.model.note

data class Note(
    val id: String,
    val title: String,
    val fields: List<NoteField>
)