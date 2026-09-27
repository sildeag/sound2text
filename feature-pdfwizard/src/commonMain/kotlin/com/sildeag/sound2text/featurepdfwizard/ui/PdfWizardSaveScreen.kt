package com.sildeag.sound2text.featurepdfwizard.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.uicommon.state.PdfWizardWorkflowState
import com.sildeag.sound2text.featurepdfwizard.controller.PdfWizardController
@Composable
fun PdfWizardSaveScreen(
    state: PdfWizardWorkflowState,
    controller: PdfWizardController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Save Complete", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        Text("Your PDF has been saved successfully.")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { controller.onError("Restarting wizard") }) {
            Text("Start Over")
        }
    }
}
