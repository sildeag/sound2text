package com.sildeag.sound2text.stt
class AndroidSpeechService : SpeechToTextService {
    override suspend fun transcribe(audioData: ByteArray): String {
        // TODO: integrate real Android STT or reuse shared engines
        return "Android STT transcription (stub)"
    }
}
