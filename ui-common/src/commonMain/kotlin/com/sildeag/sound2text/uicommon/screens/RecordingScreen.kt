package com.sildeag.sound2text.uicommon.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecordingScreen(
    onOpenPdf: (String, Int) -> Unit,
    onOpenSettings: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Recording Screen Placeholder")

        Spacer(Modifier.height(16.dp))

        Button(onClick = { onOpenPdf("placeholder.pdf", 0) }) {
            Text("Open PDF")
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = onOpenSettings) {
            Text("Settings")
        }
    }
}
