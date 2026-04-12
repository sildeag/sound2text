package com.sildeag.sound2text.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sildeag.sound2text.coreui.viewmodel.CoreSoundViewModel
import com.sildeag.sound2text.stt.SpeechToTextService
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AndroidSoundViewModel(
    private val core: CoreSoundViewModel,
    private val stt: SpeechToTextService
) : ViewModel() {
    val state: StateFlow<SttState> = core.state
    fun onEvent(event: SttEvent) {
        viewModelScope.launch {
            core.onEvent(event)
        }
    }
    fun startListening() {
        viewModelScope.launch {
            stt.start()
        }
    }
    fun stopListening() {
        viewModelScope.launch {
            stt.stop()
        }
    }
}
