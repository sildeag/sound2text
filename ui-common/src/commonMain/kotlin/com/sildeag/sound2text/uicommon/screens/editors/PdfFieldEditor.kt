package com.sildeag.sound2text.uicommon.screens.editors

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.core.notes.NotePdfField

@Composable
fun PdfFieldEditor(
    field: NotePdfField,
    onUpdate: (NotePdfField) -> Unit
) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp)) {

            Text("PDF Field", style = MaterialTheme.typography.titleSmall)
            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = field.pdfFieldName,
                onValueChange = { onUpdate(field.copy(pdfFieldName = it)) },
                label = { Text("PDF Field Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(
                value = field.value ?: "",
                onValueChange = { onUpdate(field.copy(value = it)) },
                label = { Text("Value") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
