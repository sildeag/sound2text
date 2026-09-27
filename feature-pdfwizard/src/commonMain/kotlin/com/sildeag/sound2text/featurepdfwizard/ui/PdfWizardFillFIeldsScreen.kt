package com.sildeag.sound2text.featurepdfwizard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.featurepdfwizard.controller.PdfWizardController
import com.sildeag.sound2text.uicommon.state.PdfWizardWorkflowState

@Composable
fun PdfWizardFillFieldsScreen(
    state: PdfWizardWorkflowState,
    controller: PdfWizardController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Fill PDF Fields", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        state.fields.forEach { field ->
            var value by remember { mutableStateOf(field.value) }
            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                label = { Text(field.name) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(12.dp))
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = { controller.onFieldsCompleted() }) {
            Text("Review")
        }
    }
}