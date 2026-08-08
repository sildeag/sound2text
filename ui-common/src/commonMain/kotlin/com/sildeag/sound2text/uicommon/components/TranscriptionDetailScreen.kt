package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.uicommon.models.UiTranscript
@Composable
fun TranscriptDetailScreen(
    transcript: UiTranscript
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Transcript Detail", modifier = Modifier.padding(bottom
        = 24.dp))
        Text(transcript.text)
        Spacer(Modifier.height(16.dp))
        Text("Duration: ${transcript.durationMs} ms")
    }
}