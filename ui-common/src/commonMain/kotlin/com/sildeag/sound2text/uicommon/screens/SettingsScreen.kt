package com.sildeag.sound2text.uicommon.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun SettingsScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Settings", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        Text("Sound2Text Settings will go here.")
    }
}

/*
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sildeag.sound2text.core.stt.SttConfig
// TODO: remove engine
importRegistry

// Note: Dropdown component would need to be implemented or imported
@Composable
fun SettingsScreen(
    config: SttConfig,
    onConfigChange: (SttConfig) -> Unit
) {
    Column {
        Text("Engine")
        // Placeholder for Dropdown - you might want to use a shared component here
        Text("Selected Engine: ${config.engineId}")
        
        Text("Language")
        Text("Selected Language: ${config.language}")

        Text("Sample Rate")
        Slider(
            value = config.sampleRate,
            onValueChange = { sr -> onConfigChange(config.copy(sampleRate = sr)) },
            valueRange = 8000f..48000f
        )
    }
}
*/