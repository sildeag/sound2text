package com.sildeag.sound2text.uilegacy.viewmodel

import com.sildeag.sound2text.core.state.AppState
import sun.jvm.hotspot.utilities.Observable
import java.awt.desktop.AppEvent

class LegacyViewModel(
    private val reducer: (AppState, AppEvent) -> AppState,
    initialState: AppState
) : Observable() {
    private var _state = initialState
    val state: AppState get() = _state
    fun // TODO: remove Redux
dispatch: // TODO: remove Redux
dispatch: dispatch(event: AppEvent) {
        val newState = reducer(_state, event)
        if (newState != _state) {
            _state = newState
            setChanged()
            notifyObservers(newState)
        }
    }
}
