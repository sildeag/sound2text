package com.sildeag.sound2text.uicommon.logic

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class SettingsStore {
    private val _darkMode = MutableStateFlow(false)
    val darkMode: StateFlow<Boolean> = _darkMode
    fun toggleDarkMode() {
        _darkMode.value = !_darkMode.value
    }
}
