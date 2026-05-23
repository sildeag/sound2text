package com.sildeag.sound2text.uicommon.ui.pdf

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.core.pdf.PdfFormDescriptor
import com.sildeag.sound2text.core.pdf.PdfFormDiscovery

@Composable
fun PdfFormPreview(
    discovery: PdfFormDiscovery,
    basePath: String,
    onSelectForm: (PdfFormDescriptor) -> Unit
) {
    var forms by remember { mutableStateOf<List<PdfFormDescriptor>>(emptyList()) }
    LaunchedEffect(discovery, basePath) {
        forms = discovery.discoverForms(basePath)
    }
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Discovered Forms", style = MaterialTheme.typography.titleLarge)
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
                        Text(form.formName, style =
                            MaterialTheme.typography.titleMedium)
                        Text(form.path, style = MaterialTheme.typography.bodySmall)
                        Text("Fields: ${form.fields.size}", style =
                            MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
