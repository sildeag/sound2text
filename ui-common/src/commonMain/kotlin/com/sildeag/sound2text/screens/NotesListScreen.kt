package sound2text.uicore.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sound2text.core.model.Note
import sound2text.uicore.components.NoteCard
@Composable
fun NoteListScreen(
    notes: List<Note>,
    onNoteSelected: (Note) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(notes) { note ->
            NoteCard(
                note = note,
                modifier = Modifier.padding(8.dp),
                onClick = onNoteSelected
            )
        }
    }
}