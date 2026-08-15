package com.sildeag.sound2text.uicommon.viewmodels

import com.sildeag.sound2text.uicommon.state.NavigationState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
class NavigationViewModel(
    initial: String = "home"
) {
    private val nav = NavigationState(initial)
    private val _route = MutableStateFlow(nav.current)
    val route: StateFlow<String> = _route
    fun push(route: String) {
        nav.push(route)
        _route.update { nav.current }
    }
    fun pop() {
        nav.pop()
        _route.update { nav.current }
    }
    fun replace(route: String) {
        nav.replace(route)
        _route.update { nav.current }
    }
    fun reset(route: String) {
        nav.reset(route)
        _route.update { nav.current }
    }
}