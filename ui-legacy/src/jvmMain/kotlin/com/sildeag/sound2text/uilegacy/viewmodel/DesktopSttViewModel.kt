package com.sildeag.sound2text.uilegacy.viewmodel

import com.sildeag.sound2text.core.stt.SpeechToTextService
import com.sildeag.sound2text.uicommon.logic.PulseLogic
import com.sildeag.sound2text.uicommon.logic.TranscriptionController
import com.sildeag.sound2text.uilegacy.audio.DesktopAudioRecorder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DesktopSttViewModel : KoinComponent {
    private val audio: DesktopAudioRecorder by inject()
    private val stt: SpeechToTextService by inject()
    val pulse: PulseLogic by inject()
    private val controller: TranscriptionController by inject()

    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    fun toggle() {
        if (pulse.listening.value) stop() else start()
    }

    private fun start() {
        viewModelScope.launch {
            controller.start(audio.start()).collect { partial ->
                _text.value = partial
            }
        }
    }

    private fun stop() {
        controller.stop()
        audio.stop()
    }
}
