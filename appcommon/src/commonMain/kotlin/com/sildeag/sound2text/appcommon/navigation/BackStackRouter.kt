package com.sildeag.sound2text.appcommon.navigation

import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
class BackstackRouter {
    // Stack of route names
    private val backstack = mutableStateListOf<String>()
    // Map of route → Composable content
    private val routes = mutableMapOf<String, @Composable () -> Unit>()
    fun register(
        route: String,
        content: @Composable () -> Unit
    ) {
        routes[route] = content
    }
    fun navigate(route: String) {
        backstack.add(route)
    }
    fun canPop(): Boolean {
        return backstack.size > 1
    }

    fun replace(route: String) {
        if (backstack.isNotEmpty()) {
            backstack.removeAt(backstack.lastIndex)
        }
        backstack.add(route)
    }
    fun pop() {
        if (backstack.isNotEmpty()) {
            backstack.removeAt(backstack.lastIndex)
        }
    }
    @Composable
    fun Render(startDestination: String) {
        val activeRoute =
            if (backstack.isEmpty()) startDestination
            else backstack.last()
        routes[activeRoute]?.invoke()
    }
}
