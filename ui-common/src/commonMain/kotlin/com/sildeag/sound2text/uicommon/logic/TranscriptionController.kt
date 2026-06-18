package com.sildeag.sound2text.uicommon.logic

import kotlinx.coroutines.flow.Flow
class TranscriptionController(
    private val stt: SpeechToTextService,
    private val pulse: PulseLogic
) {
    fun start(audio: Flow<ShortArray>): Flow<String> {
        pulse.toggle()
        return stt.transcribe(audio)
    }
    fun stop() {
        pulse.toggle()
        stt.stop()
    }
}
