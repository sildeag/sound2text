package com.sildeag.sound2text.core.serialization

import kotlinx.serialization.Serializable

@Serializable
sealed class NoteFieldPayload {
    @Serializable data class Text(...)
    @Serializable data class Checkbox(...)
    @Serializable data class Dropdown(...)
    @Serializable data class Pdf(...)
}