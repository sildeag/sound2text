package com.sildeag.sound2text.featurerecording.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.featurerecording.recording.RecordingState
import com.sildeag.sound2text.uicommon.ui.screens.WaveformRenderer

@Composable
fun RecordingScreen(
    viewModel: SttRecordingViewModel,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val waveform by viewModel.waveform.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Waveform visualization
        WaveformRenderer(
            amplitudes = waveform,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )
        // Partial text (live transcription)
        if (uiState.partialText.isNotEmpty()) {
            Text(
                text = uiState.partialText,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        // Final text (after processing)
        if (uiState.finalText.isNotEmpty()) {
            Text(
                text = uiState.finalText,
                style = MaterialTheme.typography.titleMedium
            )
        }
        // Error message
        uiState.errorMessage?.let { msg ->
            Text(
                text = msg,
                color = MaterialTheme.colorScheme.error
            )
        }
        // Controls
        when (uiState.recordingState) {
            RecordingState.Idle -> {
                Button(onClick = { viewModel.startRecording() }) {
                    Text("Start Recording")
                }
            }
            RecordingState.Recording -> {
                Button(onClick = { viewModel.stopRecording() }) {
                    Text("Stop Recording")
                }
            }
            RecordingState.Processing -> {
                CircularProgressIndicator()
            }
            is RecordingState.Error -> {
                Button(onClick = { viewModel.startRecording() }) {
                    Text("Retry")
                }
            }
            RecordingState.Starting -> {
                CircularProgressIndicator()
            }
        }
    }
}