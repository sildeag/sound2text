package com.sildeag.sound2text.core.service.note

interface NoteEditor {
    fun append(text: String)
    fun getText(): String
    fun setText(newText: String)
    fun clear()
    // Future AI grammar check
    fun grammarCheck(): String
}