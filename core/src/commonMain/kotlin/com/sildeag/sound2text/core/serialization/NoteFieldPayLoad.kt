package com.sildeag.sound2text.core.serialization

import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
sealed class NoteFieldPayload {

    @kotlinx.serialization.Serializable

    data class Text(
        val text: String,
        val voiceEnabled: Boolean
    ) : NoteFieldPayload()

    @Serializable
    data class Checkbox(
        val checked: Boolean
    ) : NoteFieldPayload()

    @kotlinx.serialization.Serializable
    data class Dropdown(
        val options: List<String>,
        val selected: String?
    ) : NoteFieldPayload()

    @kotlinx.serialization.Serializable
    data class Pdf(
        val pdfFieldName: String,
        val value: String?
    ) : NoteFieldPayload()
}
