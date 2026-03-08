package com.sildeag.sound2text.uicommon.viewmodel

import com.sildeag.sound2text.storage.StorageService
import com.sildeag.sound2text.stt.SpeechToTextService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class SoundViewModel(
    private val stt: SpeechToTextService,
    private val storage: StorageService
) {
    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text
    fun transcribe(path: String) {
        val result = stt.transcribe(path)
        _text.value = result
    }
    fun save(name: String) {
        storage.saveText(name, _text.value)
    }
}