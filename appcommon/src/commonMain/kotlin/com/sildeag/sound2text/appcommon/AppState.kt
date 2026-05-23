package com.sildeag.sound2text.appcommon

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
/**
 * Global application state shared across platforms.
 * This is UI‑free and safe for commonMain.
 */
data class AppState(
    val currentRoute: String = "home",
    val featureVisibility: Map<String, Boolean> = emptyMap(),
    val theme: ThemeMode = ThemeMode.System
) {
    enum class ThemeMode { Light, Dark, System }
}

/**
 * Mutable state holder used by AppRoot on both platforms.
 */
class AppStateHolder(initial: AppState = AppState()) {
    private val _state = MutableStateFlow(initial)
    val state: StateFlow<AppState> = _state
    fun update(transform: (AppState) -> AppState) {
        _state.value = transform(_state.value)
    }
}