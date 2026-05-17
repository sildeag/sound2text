package com.sildeag.sound2text.core.model.note

sealed interface NoteField {
    val id: String
    val anchor: String? // invisible reference / microscopic tag
    val level: Int // outline level
    val language: String? // ISO code, e.g. "en", "es"
}
