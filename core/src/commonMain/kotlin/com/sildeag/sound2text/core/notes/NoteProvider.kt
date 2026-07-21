package com.sildeag.sound2text.core.notes

interface NoteProvider {
    fun generatePDF(note: Note, outputPath: String)
}





