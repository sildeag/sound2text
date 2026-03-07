package com.sildeag.sound2text.coreui.logic

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PulseLogic {
    private val _isListening = MutableStateFlow(false)
    val isListening = _isListening.asStateFlow()
    val buttonColor = isListening.map { listening ->
        if (listening) Color.Red else Color.Green
    }
    val frameColor = isListening.map { listening ->
        if (listening) Color(0xFFAA0000) else Color(0xFF00AA00)
    }
    fun toggle() {
        _isListening.value = !_isListening.value
    }
}