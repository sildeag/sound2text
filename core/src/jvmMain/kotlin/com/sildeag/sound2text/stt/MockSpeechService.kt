package com.sildeag.sound2text.stt

class MockSpeechService : SpeechToTextService {
    override suspend fun transcribe(audioData: ByteArray): String {
        return "Mock transcription"
    }
}
