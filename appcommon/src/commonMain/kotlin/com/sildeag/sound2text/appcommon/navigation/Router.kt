package com.sildeag.sound2text.appcommon.navigation

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf

class Router {
    // Holds the current route string
    private val currentRoute = mutableStateOf<String?>(null)
    // Map of route → Composable content
    private val routes = mutableMapOf<String, @Composable () -> Unit>()
    fun register(
        route: String,
        content: @Composable () -> Unit
    ) {
        routes[route] = content
    }
    fun navigate(route: String) {
        currentRoute.value = route
    }
    @Composable
    fun Render(startDestination: String) {
        val route = currentRoute.value ?: startDestination
        routes[route]?.invoke()
    }
}