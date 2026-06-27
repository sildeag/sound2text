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
    // Keep the signature the repository expects
    fun decode(type: String, raw: String): NoteFieldPayload =
        json.decodeFromString(raw)
}

