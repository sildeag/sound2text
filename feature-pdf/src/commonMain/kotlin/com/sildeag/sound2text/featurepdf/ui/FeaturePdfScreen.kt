package com.sildeag.sound2text.featurepdf

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.core.pdf.model.PdfForm

@Composable
fun FeaturePdfScreen(
    formsMap: Map<String, List<PdfForm>>,
    pdfState: PdfFeatureState,
    onPdfSelected: (ByteArray) -> Unit,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "PDF Form Processor",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (pdfState.isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
        }

        pdfState.error?.let { error ->
            Text(
                text = "Error: $error",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Form Discovery List
        Text(
            text = "Available Forms",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            formsMap.forEach { (category, forms) ->
                item {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                items(forms) { form ->
                    OutlinedButton(
                        onClick = {
                            // Fetch/load byte array for selected form
                            // onPdfSelected(byteArray)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                    ) {
                        Text(form.toString())
                    }
                }
            }
        }

        // Extracted Text Summary
        if (pdfState.extractedText.isNotEmpty()) {
            Text(
                text = "Extracted Text Content",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pdfState.extractedText.joinToString("\n"),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}