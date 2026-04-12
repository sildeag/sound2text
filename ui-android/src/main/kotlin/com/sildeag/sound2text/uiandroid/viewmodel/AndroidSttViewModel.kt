package com.sildeag.sound2text.android.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sildeag.sound2text.core.stt.SpeechToTextService
import com.sildeag.sound2text.coreui.logic.PulseLogic
import com.sildeag.sound2text.coreui.logic.TranscriptionController
import com.sildeag.sound2text.android.audio.AndroidAudioRecorder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class AndroidSttViewModel(
    private val audio: AndroidAudioRecorder,
    private val stt: SpeechToTextService,
    val pulse: PulseLogic,
    private val controller: TranscriptionController
) : ViewModel() {
    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text
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
