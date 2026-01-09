package com.sildeag.sound2text.stt

class OpenAIWhisperService(private val apiKey: String) : SpeechToTextService {
    override suspend fun transcribe(audioData: ByteArray): String {
        return "OpenAI Whisper transcription (stub)"
    }
}