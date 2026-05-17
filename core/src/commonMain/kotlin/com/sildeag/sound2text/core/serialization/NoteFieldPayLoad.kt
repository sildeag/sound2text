package com.sildeag.sound2text.core.serialization

import kotlinx.serialization.Serializable

@Serializable
sealed class NoteFieldPayload {

    @Serializable
    data class Text(
        val text: String,
        val language: String?,
        val voiceEnabled: Boolean
    ) : NoteFieldPayload()

    @Serializable
    data class Checkbox(
        val checked: Boolean
    ) : NoteFieldPayload()

    @Serializable
    data class Dropdown(
        val selected: String?
    ) : NoteFieldPayload()

    @Serializable
    data class Pdf(
        val value: String?,
        val language: String?
    ) : NoteFieldPayload()
}
