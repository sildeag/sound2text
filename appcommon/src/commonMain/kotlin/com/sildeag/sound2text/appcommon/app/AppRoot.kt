package com.sildeag.sound2text.appcommon.app

import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import com.sildeag.sound2text.navigation.Router

@Composable
fun AppRoot(
    router: Router,
    startDestination: String = "main_menu"
) {
    MaterialTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            // Render the current route's content
            router.Render(startDestination)
        }
    }
}
