package com.sildeag.sound2text.uicommon.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    onBack: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Settings", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Text("Sound2Text Settings will go here.")

        Text("Settings Placeholder")

        Spacer(Modifier.height(16.dp))

        Button(onClick = onBack) {
            Text("Back")
        }
    }
}
