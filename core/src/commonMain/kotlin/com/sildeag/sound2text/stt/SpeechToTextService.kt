package com.sildeag.sound2text.stt

interface peechToTextService {
    suspend fun transcribe(audioData: ByteArray): String
}

