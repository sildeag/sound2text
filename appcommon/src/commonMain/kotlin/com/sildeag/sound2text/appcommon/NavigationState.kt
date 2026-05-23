package com.sildeag.sound2text.appcommon

/**
 * A simple, platform‑neutral navigation model.
 * Android maps this to NavController.
 * Desktop maps this to Voyager.
 */
data class NavigationState(
    val currentRoute: String = "home",
    val backstack: List<String> = emptyList()
) {
    fun push(route: String): NavigationState =
        copy(
            currentRoute = route,
            backstack = backstack + currentRoute
        )
    fun pop(): NavigationState =
        if (backstack.isEmpty()) this
        else copy(
            currentRoute = backstack.last(),
            backstack = backstack.dropLast(1)
        )
}