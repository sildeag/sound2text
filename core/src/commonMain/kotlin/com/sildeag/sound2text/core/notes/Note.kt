package com.sildeag.sound2text.core.notes

data class Note(
    val id: String,
    val title: String,
    val fields: List<NoteField>
) {
    val updatedAtFormatted: Any
    val previewText: Any
}
