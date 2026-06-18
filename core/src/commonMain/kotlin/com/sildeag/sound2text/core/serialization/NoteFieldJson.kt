package com.sildeag.sound2text.core.serialization

import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

object NoteFieldJson {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    fun encode(payload: NoteFieldPayload): String =
        json.encodeToString(payload)

    fun decode(raw: String): NoteFieldPayload =
        json.decodeFromString(raw)
}
