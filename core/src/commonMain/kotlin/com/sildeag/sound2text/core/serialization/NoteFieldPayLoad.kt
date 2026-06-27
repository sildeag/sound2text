package com.sildeag.sound2text.core.serialization

import kotlinx.serialization.Serializable
@Serializable
sealed class NoteFieldPayload {
    @Serializable
    data class Text(
        val text: String,
        val voiceEnabled: Boolean
    ) : NoteFieldPayload()
    @Serializable
    data class Checkbox(
        val checked: Boolean
    ) : NoteFieldPayload()
    @Serializable
    data class Dropdown(
        val options: List<String>,
        val selected: String?
    ) : NoteFieldPayload()
    @Serializable
    data class Pdf(
        val pdfFieldName: String,
        val value: String?
    ) : NoteFieldPayload()
}