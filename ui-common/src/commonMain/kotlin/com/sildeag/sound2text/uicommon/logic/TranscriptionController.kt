package com.sildeag.sound2text.uicommon.logic

import com.sildeag.sound2text.core.stt.model.SttResult
import com.sildeag.sound2text.core.stt.services.SttService
import kotlinx.coroutines.flow.Flow
class TranscriptionController(
    private val stt: SttService,
    private val pulse: PulseLogic
) {
    suspend fun start(audio: Flow<ShortArray>): SttResult {
        pulse.toggle()
        return stt.transcribe(audio)
    }
    fun stop() {
        pulse.toggle()
        stt.stop()
    }
}
