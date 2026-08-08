package com.sildeag.sound2text.uicommon.state

/**
 * A simple stack-based navigation state for KMP.
 * Platform-agnostic, DI-free, UI-only.
 */
class NavigationState(
    initial: String = "home"
) {
    private val _stack = ArrayDeque<String>().apply {
        addLast(initial)
    }

    val current: String
        get() = _stack.last()

    val canGoBack: Boolean
        get() = _stack.size > 1

    fun push(route: String) {
        _stack.addLast(route)
    }

    fun pop() {
        if (canGoBack) {
            _stack.removeLast()
        }
    }

    fun replace(route: String) {
        _stack.removeLast()
        _stack.addLast(route)
    }

    fun reset(route: String) {
        _stack.clear()
        _stack.addLast(route)
    }

    fun stackSnapshot(): List<String> = _stack.toList()
}
