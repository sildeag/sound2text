package com.sildeag.sound2text.core.notes

data class NoteTextField(
    override val id: String,
    override val anchor: String?,
    override val level: Int,
    override val language: String?,
    val text: String,
    val translations: Map<String, String> = emptyMap(), // lang -> text
    val voiceEnabled: Boolean = false
) : NoteField
