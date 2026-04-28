package com.sildeag.sound2text.uidesktop

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.sildeag.sound2text.core.pdf.PdfFormDescriptor
import com.sildeag.sound2text.core.pdf.PdfFormDiscovery
import com.sildeag.sound2text.uicommon.ui.pdf.PdfFieldEditor
import com.sildeag.sound2text.uicommon.ui.pdf.PdfFormPreview
import com.sildeag.sound2text.uicommon.ui.pdf.PdfPluginBrowser
@Composable
fun DesktopRoot() {
    MaterialTheme {
        var selectedDiscovery by remember { mutableStateOf<PdfFormDiscovery?>(null)
        }
        var selectedForm by remember { mutableStateOf<PdfFormDescriptor?>(null) }
        when {
            selectedDiscovery == null -> {
                PdfPluginBrowser { discovery ->
                    selectedDiscovery = discovery
                }
            }
            selectedForm == null -> {
                PdfFormPreview(
                    discovery = selectedDiscovery!!,
                    basePath = "forms" // adjust to your directory
                ) { form ->
                    selectedForm = form
                }
            }
            else -> {
                PdfFieldEditor(
                    form = selectedForm!!
                ) { updatedField ->
                    // You can persist or log updates here
                }
            }
        }
    }
}
