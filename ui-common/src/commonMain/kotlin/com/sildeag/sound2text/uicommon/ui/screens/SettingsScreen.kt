package com.sildeag.sound2text.uicommon.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngineRegistry

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
