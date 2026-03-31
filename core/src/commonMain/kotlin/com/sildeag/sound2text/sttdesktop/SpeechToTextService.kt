package com.sildeag.sound2text.sttdesktop

interface SpeechToTextService {
    suspend fun transcribe(audioData: ByteArray): String
}

