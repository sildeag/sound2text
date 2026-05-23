package com.sildeag.sound2text.appcommon

/**
 * Events emitted by UI layers and consumed by AppStateHolder.
 * These are platform‑neutral.
 */
sealed class AppEvent {
    data class NavigateTo(val route: String) : AppEvent()
    data class SetFeatureVisibility(
        val feature: String,
        val visible: Boolean
    ) : AppEvent()
    data class SetTheme(val theme: AppState.ThemeMode) : AppEvent()
    object Back : AppEvent()
}
