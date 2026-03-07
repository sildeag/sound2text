package com.sildeag.sound2text.service.note

class MockNoteEditor : NoteEditor {
    private val buffer = StringBuilder()
    override fun append(text: String) {
        buffer.append(text)
    }
    override fun clear() {
        buffer.clear()
    }

    override fun grammarCheck(): String {
        TODO("Not yet implemented")
    }

    override fun getText(): String {
        return buffer.toString()
    }

    override fun setText(newText: String) {
        TODO("Not yet implemented")
    }
}