package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.pdfwizard.PdfWizardState
@Composable
fun PdfWizardScreen(
    state: PdfWizardState,
    onSelectPdf: () -> Unit,
    onDiscoverFields: () -> Unit,
    onNextField: () -> Unit,
    onPrevField: () -> Unit,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onApplyStt: () -> Unit,
    onManualEntry: (String) -> Unit,
    onGeneratePdf: () -> Unit,
    onSavePdf: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("PDF Wizard", modifier = Modifier.padding(bottom =
            16.dp))
        Button(onClick = onSelectPdf) {
            Text("Select PDF")
        }
        Spacer(Modifier.height(12.dp))
        Button(onClick = onDiscoverFields) {
            Text("Discover Fields")
        }
        Spacer(Modifier.height(12.dp))
        Row {
            Button(onClick = onPrevField) { Text("Previous") }
            Spacer(Modifier.width(12.dp))
            Button(onClick = onNextField) { Text("Next") }
        }
        Spacer(Modifier.height(12.dp))
        if (!state.isRecording) {
            Button(onClick = onStartRecording) { Text("Start Recording") }
            } else {
                Button(onClick = onStopRecording) { Text("Stop Recording") }
                }
                Spacer(Modifier.height(12.dp))
                Button(onClick = onApplyStt) {
                    Text("Apply STT Result")
                }
                Spacer(Modifier.height(12.dp))
                Button(onClick = onGeneratePdf) {
                    Text("Generate PDF")
                }
                Spacer(Modifier.height(12.dp))
                Button(onClick = onSavePdf) {
                    Text("Save PDF")
                }
            }
        }