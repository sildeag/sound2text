package com.sildeag.sound2text.core.model.note

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object NoteJson {

    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = false
    }

    fun encodeList(notes: List<Note>): String =
        json.encodeToString(notes)

    fun decodeList(raw: String): List<Note> =
        json.decodeFromString(raw)
}
