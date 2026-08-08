package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.uicommon.screens.WaveformView
import com.sildeag.sound2text.uicommon.state.SoundState
@Composable
fun RecordingScreen(
    state: SoundState,
    onStart: () -> Unit,
    onStop: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        WaveformView(
            waveform = state.waveform,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            state = TODO()
        )
        if (!state.isRecording) {
            Button(onClick = onStart) {
                Text("Start Recording")
            }
        } else {
            Button(onClick = onStop) {
                Text("Stop Recording")
            }
        }
        Text(
            "Duration: ${state.durationMs} ms",
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
