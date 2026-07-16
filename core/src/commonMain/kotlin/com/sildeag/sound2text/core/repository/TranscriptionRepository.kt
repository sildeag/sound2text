package com.sildeag.sound2text.core.repository

interface TranscriptionRepository {
    suspend fun transcribe(bytes: ByteArray): String
}