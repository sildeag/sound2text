package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.pdfwizard.PdfWizardState
import com.sildeag.sound2text.uicommon.state.PdfWizardState

@Composable
fun PdfWizardFieldEditor(
    state: PdfWizardState,
    onManualEntry: (String) -> Unit
) {
    val field = state.currentField
    if (field == null) {
        Text("No field selected")
        return
    }
    var text by remember { mutableStateOf(field.value ?: "") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Editing Field: ${field.name}")
        Spacer(Modifier.height(12.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = { onManualEntry(text) }) {
            Text("Apply")
        }
    }
}
