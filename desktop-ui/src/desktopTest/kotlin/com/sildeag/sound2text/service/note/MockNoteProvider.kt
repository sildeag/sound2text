package com.sildeag.sound2text.service.note

class MockNoteProvider : NoteProvider {
    var savedNote: String? = null
    override fun load(): String {
        return savedNote ?: ""
    }
    override fun save(note: String) {
        savedNote = note
    }

    override fun generatePDF(outputPath: String) {
        TODO("Not yet implemented")
    }
}
