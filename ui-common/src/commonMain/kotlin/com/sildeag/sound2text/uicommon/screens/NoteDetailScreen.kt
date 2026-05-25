package com.sildeag.sound2text.uicommon.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.core.model.note.Note
@Composable
fun NoteDetailScreen(
    note: Note,
    onBack: () -> Unit,
    onEdit: (Note) -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(note.title, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Text(note.content ?: "", style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(16.dp))
        Row {
            Button(onClick = onBack) {
                Text("Back")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { onEdit(note) }) {
                Text("Edit")
            }
        }
    }
}
