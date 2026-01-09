package com.sildeag.sound2text.stt

class WhisperCppService : SpeechToTextService {
    override suspend fun transcribe(audioData: ByteArray): String {
        return "Whisper.cpp transcription (stub)"
    }
}