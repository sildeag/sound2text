package com.sildeag.sound2text.featuresettings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val state by viewModel.state.collectAsState()
    Column {
        Text("Speech Engine")
        DropdownMenu(
            options = state.availableEngines,
            selected = state.selectedEngine,
            onSelect = { viewModel.updateEngine(it) }
        )
        Text("PDF Processor")
        DropdownMenu(
            options = state.availablePdfProcessors,
            selected = state.selectedPdfProcessor,
            onSelect = { viewModel.updatePdfProcessor(it) }
        )
        Text("Language")
        DropdownMenu(
            options = listOf("en", "es", "fr"),
            selected = state.language,
            onSelect = { viewModel.updateLanguage(it) }
        )
    }
}
