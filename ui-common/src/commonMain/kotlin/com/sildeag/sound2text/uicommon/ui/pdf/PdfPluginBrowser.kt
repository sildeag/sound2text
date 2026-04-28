package com.sildeag.sound2text.uicommon.ui.pdf

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.core.pdf.PdfFormDiscovery
import org.koin.compose.koinInject
@Composable
fun PdfPluginBrowser(
    onSelect: (PdfFormDiscovery) -> Unit
) {
    val discoveries: List<PdfFormDiscovery> = koinInject()
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("PDF Plugins", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        discoveries.forEach { discovery ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onSelect(discovery) }
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text(discovery::class.simpleName ?: "Unknown plugin")
                }
            }
        }
    }
}
