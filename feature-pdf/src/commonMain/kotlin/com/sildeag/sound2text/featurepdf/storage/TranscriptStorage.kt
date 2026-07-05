package com.sildeag.sound2text.featurerecording.storage

interface TranscriptStorage {
    suspend fun saveTranscript(text: String)
}