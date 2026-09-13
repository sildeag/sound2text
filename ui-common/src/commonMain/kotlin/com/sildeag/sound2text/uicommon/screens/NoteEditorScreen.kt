package com.sildeag.sound2text.uicommon.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.core.notes.*
import com.sildeag.sound2text.uicommon.screens.editors.PdfFieldEditor
import kotlinx.datetime.Clock.System
import java.util.UUID
@Composable
fun NoteEditorScreen(
    initial: Note?,
    onSave: (Note) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf(initial?.title ?: "") }
    var fields by remember { mutableStateOf(initial?.fields ?:
    emptyList()) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Note") },
                navigationIcon = {
                    IconButton(onClick = onCancel) { Text("<") }
                },
                actions = {
                    IconButton(onClick = {
                        val note = Note(
                            id = initial?.id ?:
                            UUID.randomUUID().toString(),
                            title = title,
                            fields = fields,
                            updatedAt = System.now()
                        )
                        onSave(note)
                    }) {
                        Text("Save")
                    }
                }
            )
        },
        floatingActionButton = {
            FieldAddMenu { newField ->
                fields = fields + newField
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            item {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
            }
            itemsIndexed(fields) { index, field ->
                when (field) {

                    is NoteTextField ->
                        TextFieldEditor(field) { updated ->
                            fields = fields.toMutableList().apply { set(index, updated) }
                        }

                    is NoteCheckboxField ->
                        CheckboxFieldEditor(field) { updated ->
                            fields = fields.toMutableList().apply { set(index, updated) }
                        }

                    is NoteDropdownField ->
                        DropdownFieldEditor(field) { updated ->
                            fields = fields.toMutableList().apply { set(index, updated) }
                        }

                    is NotePdfField ->
                        PdfFieldEditor(field) { updated ->
                            fields = fields.toMutableList().apply { set(index, updated) }
                        }
                }

                Spacer(Modifier.height(12.dp))

                TextButton(
                    onClick = {
                        fields = fields.toMutableList().apply { removeAt(index) }
                    }
                ) {
                    Text("Delete Field")
                }

                Spacer(Modifier.height(16.dp))
            }

        }
    }
}