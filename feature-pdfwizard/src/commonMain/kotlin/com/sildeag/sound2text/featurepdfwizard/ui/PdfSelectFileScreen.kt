package com.sildeag.sound2text.featurepdfwizard.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.featurepdfwizard.controller.PdfWizardController
@Composable
fun PdfWizardSelectFileScreen(
    controller: PdfWizardController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Select a PDF to begin", style =
            MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            // Replace with your file picker
            controller.onFileSelected("/path/to/document.pdf")
        }) {
            Text("Choose PDF")
        }
    }
}
