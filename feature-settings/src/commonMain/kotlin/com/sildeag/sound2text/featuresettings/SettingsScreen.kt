package com.sildeag.sound2text.featuresettings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.runtime.Composable
import com.sildeag.sound2text.core.serialization.NoteFieldPayload


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
        NoteFieldPayload.Text(
            "Language",
            voiceEnabled = true
        )
        DropdownMenu(
            options = listOf("en", "es", "fr"),
            selected = state.language,
            onSelect = { viewModel.updateLanguage(it) }
        )
    }
}

@Composable
fun Column(content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}

@Composable
fun DropdownMenu(options: List<String>, selected: String?, onSelect: () -> Unit) {
    TODO("Not yet implemented")
}
