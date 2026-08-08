package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun HomeScreen(
    onRecording: () -> Unit,
    onTranscripts: () -> Unit,
    onPdfWizard: () -> Unit,
    onSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Sound2Text", modifier = Modifier.padding(bottom =
            24.dp))
        Button(onClick = onRecording, modifier =
            Modifier.fillMaxWidth()) {
            Text("Recording")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onTranscripts, modifier =
            Modifier.fillMaxWidth()) {
            Text("Transcripts")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onPdfWizard, modifier =
            Modifier.fillMaxWidth()) {
            Text("PDF Wizard")
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = onSettings, modifier =
            Modifier.fillMaxWidth()) {
            Text("Settings")
        }
    }
}