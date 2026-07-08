package com.sildeag.sound2text.appcommon.app

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.sildeag.sound2text.navigation.BackstackRouter
import com.sildeag.sound2text.navigation.BackstackAppBar

@Composable
fun AppScaffold(
    router: BackstackRouter,
    startDestination: String = "main_menu",
    titleForRoute: (String) -> String
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            val canGoBack = router.canPop()
            val activeRoute = router.peekOr(startDestination)
            BackstackAppBar(
                router = router,
                title = titleForRoute(activeRoute),
                canGoBack = canGoBack
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            router.Render(startDestination)
        }
    }
}
