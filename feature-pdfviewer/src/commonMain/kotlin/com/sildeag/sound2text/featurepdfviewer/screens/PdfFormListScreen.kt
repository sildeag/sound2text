package com.sildeag.sound2text.featurepdfviewer.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.core.serialization.NoteFieldPayload
import com.sildeag.sound2text.featurepdfviewer.models.UiPdfForm
import com.sildeag.sound2text.featurepdfviewer.viewmodels.PdfFormListViewModel


@Composable
fun PdfFormListScreen(
    viewModel: PdfFormListViewModel,
    onSelectForm: (UiPdfForm) -> Unit
) {
    val forms by viewModel.forms.collectAsState()
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Discovered Forms", style =
            MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        if (forms.isEmpty()) {
            Text("No forms found.")
        } else {
            forms.forEach { form ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { onSelectForm(form) }
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text(
                            form.name, style =
                                MaterialTheme.typography.titleMedium
                        )
                        Text(form.path, style =
                            MaterialTheme.typography.bodySmall)
                        Text("Fields: ${form.fieldCount}", style =
                            MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
