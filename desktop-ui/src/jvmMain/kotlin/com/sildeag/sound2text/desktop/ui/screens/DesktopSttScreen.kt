package com.sildeag.sound2text.desktop.ui.screens

import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import org.koin.compose.koinInject
import com.sildeag.sound2text.desktop.viewmodel.DesktopSttViewModel

@Composable
fun DesktopSttScreen(
    vm: DesktopSttViewModel = koinInject()
) {
    val text by vm.text.collectAsState()
    val listening by vm.listening.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text)

        Spacer(Modifier.height(16.dp))

        Button(onClick = { vm.toggle() }) {
            Text(if (listening) "Stop" else "Start")
        }
    }
}
