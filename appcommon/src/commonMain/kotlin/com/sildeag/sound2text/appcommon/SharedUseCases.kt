package com.sildeag.sound2text.appcommon

/**
 * Cross‑platform business logic helpers.
 * These are called from AppRoot or platform navigation hosts.
 */
class SharedUseCases(
    private val stateHolder: AppStateHolder
) {
    fun handleEvent(event: AppEvent) {
        when (event) {
            is AppEvent.NavigateTo -> {
                stateHolder.update { it.copy(currentRoute =
                    event.route) }
            }
            is AppEvent.SetFeatureVisibility -> {
                stateHolder.update {
                    it.copy(
                        featureVisibility = it.featureVisibility +
                                (event.feature to event.visible)
                    )
                }
            }
            is AppEvent.SetTheme -> {
                stateHolder.update { it.copy(theme = event.theme) }
            }
            AppEvent.Back -> {
                // UI layers handle actual back navigation.
                // This event is here for symmetry.
            }
        }
    }
}