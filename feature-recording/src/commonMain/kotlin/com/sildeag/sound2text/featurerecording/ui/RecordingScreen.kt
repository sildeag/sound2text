package com.sildeag.sound2text.featurerecording.ui

import androidx.compose.runtime.Composable


@Composable
fun RecordingScreen(stt: SttService) {
    val recordingState by stt.recordingState.collectAsState()
    val waveformState by stt.waveformState.collectAsState()
    Column(Modifier.padding(16.dp)) {
        // Recording indicator + timer
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (recordingState.isRecording) {
                RecordingIndicator() // pulsing red dot
                Spacer(Modifier.width(8.dp))
                Text(formatElapsed(recordingState.elapsedMs))
            }
        }
        Spacer(Modifier.height(16.dp))
        // Waveform
        WaveformRenderer(
            amplitudes = waveformState.amplitudes,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )
        Spacer(Modifier.height(16.dp))
        // Transcription
        TranscriptionView(
            text = recordingState.text,
            isFinal = recordingState.isFinal
        )
        Spacer(Modifier.height(24.dp))
        Row {
            Button(onClick = { stt.start() }) { Text("Start") }
            Spacer(Modifier.width(16.dp))
            Button(onClick = { stt.stop() }) { Text("Stop") }
        }
    }
}
