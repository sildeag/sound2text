package com.sildeag.sound2text.uicommon.pdf

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.core.pdf.PdfFormDescriptor
@Composable
fun PdfFieldEditor(
    form: PdfFormDescriptor,
    onUpdateField: (PdfFieldDescriptor) -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Edit Fields: ${form.formName}", style =
            MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        form.fields.forEach { field ->
            var language by remember(field.name) { mutableStateOf(field.language ?:
            "") }
            var voiceEnabled by remember(field.name)
            { mutableStateOf(field.voiceEnabled) }
            Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Column(Modifier.padding(12.dp)) {
                    Text(field.name, style = MaterialTheme.typography.titleMedium)
                    Text("Type: ${field.type}", style =
                        MaterialTheme.typography.bodySmall)
                    Spacer(Modifier.height(4.dp))
                    OutlinedTextField(
                        value = language,
                        onValueChange = {
                            language = it
                            onUpdateField(field.copy(language = it))
                        },
                        label = { Text("Language (optional)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Voice enabled")
                        Switch(
                            checked = voiceEnabled,
                            onCheckedChange = {
                                voiceEnabled = it
                                onUpdateField(field.copy(voiceEnabled = it))
                            }
                        )
                    }
                }
            }
        }
    }
}
