package com.sildeag.sound2text.uidesktop.logging

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DebugLogOverlay(logCollector: LogCollector) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.Black.copy(alpha = 0.7f))
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text("Debug Logs", color = Color.White)
            Spacer(Modifier.height(8.dp))
            logCollector.entries.forEach {
                Text(it, color = Color.LightGray)
            }
        }
    }
}
