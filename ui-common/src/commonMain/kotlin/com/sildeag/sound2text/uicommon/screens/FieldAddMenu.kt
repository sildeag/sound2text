package com.sildeag.sound2text.uicommon.screens
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import com.sildeag.sound2text.core.notes.*
import java.util.UUID
@Composable
fun FieldAddMenu(onAdd: (NoteField) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Box {
        FloatingActionButton(onClick = { expanded = true }) {
            Text("+")
        }
        DropdownMenu(expanded = expanded, onDismissRequest =
            { expanded = false }) {
            DropdownMenuItem(
                text = { Text("Add Text Field") },
                onClick = {
                    expanded = false
                    onAdd(
                        NoteTextField(
                            id = UUID.randomUUID().toString(),
                            anchor = null,
                            level = 0,
                            language = "en",
                            text = ""
                        )
                    )
                }
            )
            DropdownMenuItem(
                text = { Text("Add Checkbox Field") },
                onClick = {
                    expanded = false
                    onAdd(
                        NoteCheckboxField(
                            id = UUID.randomUUID().toString(),
                            anchor = null,
                            level = 0,
                            language = "en",
                            label = "Checkbox",
                            checked = false
                        )
                    )
                }
            )
            DropdownMenuItem(
                text = { Text("Add Dropdown Field") },
                onClick = {
                    expanded = false
                    onAdd(
                        NoteDropdownField(
                            id = UUID.randomUUID().toString(),
                            anchor = null,
                            level = 0,
                            language = "en",
                            label = "Dropdown",
                            options = listOf("Option 1", "Option 2"),
                            selected = null
                        )
                    )
                }
            )
            DropdownMenuItem(
                text = { Text("Add PDF Field") },
                onClick = {
                    expanded = false
                    onAdd(
                        NotePdfField(
                            id = UUID.randomUUID().toString(),
                            anchor = null,
                            level = 0,
                            language = "en",
                            pdfFieldName = "FieldName",
                            value = null
                        )
                    )
                }
            )
        }
    }
}
