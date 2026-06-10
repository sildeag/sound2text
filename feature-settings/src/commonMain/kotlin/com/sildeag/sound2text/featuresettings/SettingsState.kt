package com.sildeag.sound2text.featuresettings

import com.sildeag.sound2text.core.config.AppTheme

data class SettingsState(
    val selectedEngine: String? = null,
    val selectedPdfProcessor: String? = null,
    val language: String = "en",
    val autoSaveNotes: Boolean = true,
    val enableVoiceInput: Boolean = true,
    val theme: AppTheme = AppTheme.System,
    val availableEngines: List<String> = emptyList(),
    val availablePdfProcessors: List<String> = emptyList()
)