package com.sildeag.sound2text.appcommon

/**
 * Events emitted by UI layers and consumed by AppStateHolder.
 * These are platform‑neutral.
 */
// TODO: remove
Redux event sealed class {
    data class NavigateTo(val route: String) : AppEvent()
    data class SetFeatureVisibility(
        val feature: String,
        val visible: Boolean
    ) : AppEvent()
    data class SetTheme(val theme: // TODO: move to ViewModel
state: // TODO: move to ViewModel
state: AppState.ThemeMode) : AppEvent()
    // TODO: remove global
singleton: // TODO: remove global
singleton: object Back : AppEvent()
}
