package com.sildeag.sound2text.stt

interface SpeechToTextService {
    suspend fun transcribe(audioData: ByteArray): String
}

