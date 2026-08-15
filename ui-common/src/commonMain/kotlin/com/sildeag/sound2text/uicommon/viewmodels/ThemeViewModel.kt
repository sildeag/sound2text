package com.sildeag.sound2text.uicommon.viewmodels

import com.sildeag.sound2text.uicommon.state.ThemeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
class ThemeViewModel {
    private val _state = MutableStateFlow(ThemeState())
    val state: StateFlow<ThemeState> = _state
    fun toggleDarkMode() {
        _state.update { it.copy(isDarkMode = !it.isDarkMode) }
    }
    fun setAccent(color: Long) {
        _state.update { it.copy(accentColor = color) }
    }
    fun setFontScale(scale: Float) {
        _state.update { it.copy(fontScale = scale) }
    }
}
