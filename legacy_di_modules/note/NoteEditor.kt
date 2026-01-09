package com.sildeag.sound2text.service.note

class NoteEditor {
    private val builder = StringBuilder()
    fun append(text: String) {
        if (builder.isNotEmpty()) builder.append(" ")
        builder.append(text.trim())
    }
    fun setText(text: String) {
        builder.clear()
        builder.append(text.trim())
    }
    fun getText(): String = builder.toString()
    fun clear() {
        builder.clear()
    }
    fun grammarCheck(): String {
        // Placeholder for future grammar engine
        // For now, just normalize spacing and capitalization
        val cleaned = builder.toString()
            .replace(Regex("\\s+"), " ")
            .trim()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase()
            else it.toString() }
        setText(cleaned)
        return cleaned
    }
}
