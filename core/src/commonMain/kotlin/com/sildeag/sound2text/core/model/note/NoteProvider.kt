package com.sildeag.sound2text.core.model.note

interface NoteProvider {
    fun generatePDF(note: Note, outputPath: String)
}





