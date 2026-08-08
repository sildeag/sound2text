package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.uicommon.state.ThemeState
@Composable
fun SettingsScreen(
    state: ThemeState,
    onToggleDark: () -> Unit,
    onFontScale: (Float) -> Unit,
    onAccent: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Settings", modifier = Modifier.padding(bottom = 24.dp))
        Button(onClick = onToggleDark) {
            Text("Toggle Dark Mode")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { onFontScale(1.1f) }) {
            Text("Increase Font Size")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { onAccent(0xFF00BCD4) }) {
            Text("Set Accent: Cyan")
        }
    }
}
