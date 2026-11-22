package com.sildeag.sound2text.uicommon.pdf.storage

interface TranscriptStorage {
    suspend fun saveTranscript(text: String)
}