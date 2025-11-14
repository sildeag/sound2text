package com.sildeag.sound2text.service.note

class NoteEditorImpl : NoteEditor {
    private val textBuffer = StringBuilder()
    override fun append(text: String) {
        println("appended text $text")
        textBuffer.appendLine(text)
    }
    override fun getText(): String = textBuffer.toString()
    override fun setText(newText: String) {
        textBuffer.clear()
        textBuffer.append(newText)
    }
    override fun clear() {
        textBuffer.clear()
    }
    // Future AI grammar check
    override fun grammarCheck(): String {
        TODO("Integrate AI grammar correction here")
    }
}