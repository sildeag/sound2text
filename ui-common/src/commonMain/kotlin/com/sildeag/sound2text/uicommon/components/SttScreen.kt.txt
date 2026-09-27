package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.uicommon.state.SttUiState
@Composable
fun SttScreen(
    state: SttUiState,
    onStart: () -> Unit,
    onStop: () -> Unit,
    onApplyPartial: () -> Unit,
    onApplyFinal: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Speech‑to‑Text", modifier = Modifier.padding(bottom =
            16.dp))
        if (!state.isRecording) {
            Button(onClick = onStart) { Text("Start Recording") }
        } else {
            Button(onClick = onStop) { Text("Stop Recording") }
        }
        Spacer(Modifier.height(16.dp))
        Text("Partial: ${state.partial}")
        Spacer(Modifier.height(8.dp))
        Button(onClick = onApplyPartial) { Text("Apply Partial") }
        Spacer(Modifier.height(16.dp))
        Text("Final: ${state.finalText}")
        Spacer(Modifier.height(8.dp))
        Button(onClick = onApplyFinal) { Text("Apply Final") }
    }
}
