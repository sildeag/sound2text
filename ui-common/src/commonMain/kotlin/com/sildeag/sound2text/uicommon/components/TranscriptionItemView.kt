package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.uicommon.models.UiTranscript
@Composable
fun TranscriptItemView(
    item: UiTranscript,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(12.dp)
    ) {
        Text(item.text)
        Text(
            "Duration: ${item.durationMs} ms",
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}