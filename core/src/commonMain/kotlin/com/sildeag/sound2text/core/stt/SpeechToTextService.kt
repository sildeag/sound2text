package com.sildeag.sound2text.core.stt

interface SpeechToTextService {
    suspend fun transcribe(audioData: ByteArray): String
}

