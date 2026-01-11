package com.sildeag.sound2text.desktop.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sildeag.sound2text.desktop.viewmodel.DesktopSttViewModel
import org.koin.compose.koinInject

@Composable
fun DesktopSttScreen(vm: DesktopSttViewModel = koinInject()) {
    val text by vm.text.collectAsState()
    val pulse = vm.pulse // Assuming pulse is accessible from vm or injected
    val listening by pulse.listening.collectAsState()
    
    // Placeholder colors if not directly in PulseLogic or if they need to be resolved
    val buttonColor = Color.Green 
    val frameColor = Color(0xFF00AA00)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(frameColor)
    ) {
        Column {
            Text(text)
            Button(
                onClick = { vm.toggle() },
                colors = ButtonDefaults.buttonColors(buttonColor)
            ) {
                Text(if (listening) "Stop" else "Start")
            }
        }
    }
}
