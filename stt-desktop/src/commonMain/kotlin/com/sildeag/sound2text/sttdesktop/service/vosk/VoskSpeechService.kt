package com.sildeag.sound2text.sttdesktop.service.vosk

import com.sildeag.sound2text.core.stt.SpeechToTextService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class VoskSpeechService(
    private val recognizer: VoskLiveRecognizer
) : SpeechToTextService {
    private val output = MutableSharedFlow<String>()
    override fun transcribe(audio: Flow<ShortArray>): Flow<String> {
        // TODO: feed audio into recognizer
        return output
    }
    override fun stop() {
        recognizer.stop()
    }
}