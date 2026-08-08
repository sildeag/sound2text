package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.sildeag.sound2text.uicommon.models.UiTranscript
@Composable
fun TranscriptListView(
    items: List<UiTranscript>,
    onSelect: (UiTranscript) -> Unit
) {
    LazyColumn {
        items(items) { item ->
            TranscriptItemView(
                item = item,
                onClick = { onSelect(item) }
            )
        }
    }
}
