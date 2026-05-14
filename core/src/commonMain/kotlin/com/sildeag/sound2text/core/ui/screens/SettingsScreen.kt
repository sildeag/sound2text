package com.sildeag.sound2text.core.ui.screens

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttEngineRegistry

@Composable
fun SettingsScreen(
    config: SttConfig,
    onConfigChange: (SttConfig) -> Unit
) {
    Column {
        Text("Engine")
        Dropdown(
            items = SttEngineRegistry.all(),
            selected = config.engineId,
            onSelect = { id -> onConfigChange(config.copy(engineId = id)) }
        )
        Text("Language")
        Dropdown(
            items = listOf("en", "es", "fr", "de"),
            selected = config.language,
            onSelect = { lang -> onConfigChange(config.copy(language = lang)) }
        )
        Text("Sample Rate")
        Slider(
            value = config.sampleRate,
            onValueChange = { sr -> onConfigChange(config.copy(sampleRate = sr)) },
            valueRange = 8000f..48000f
        )
    }
}