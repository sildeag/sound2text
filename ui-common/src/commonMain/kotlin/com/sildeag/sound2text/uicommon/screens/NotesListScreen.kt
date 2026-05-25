package com.sildeag.sound2text.uicommon.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sildeag.sound2text.core.model.Note
import com.sildeag.sound2text.uicommon.components.NoteCard

@Composable
fun NoteListScreen(
    notes: List<Note>,
    onNoteSelected: (Note) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(notes) { note ->
            NoteCard(
                note = note,
                modifier = padding(8.dp),
                onClick = onNoteSelected
            )
        }
    }
}
