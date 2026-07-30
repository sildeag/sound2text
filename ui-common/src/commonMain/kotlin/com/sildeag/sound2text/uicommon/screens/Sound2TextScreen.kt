package com.sildeag.sound2text.uicommon.ui

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.uicommon.viewmodel.CoreSoundViewModel
import com.sildeag.sound2text.uicommon.state.SttState

@Composable
fun Sound2TextScreen(viewModel: CoreSoundViewModel) {
    val sttState by viewModel.state.collectAsState()
    val transcript by viewModel.transcript.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = when (sttState) {
                SttState.Idle -> "Idle"
                SttState.Listening -> "Listening..."
                SttState.Processing -> "Processing..."
                is SttState.Error -> "Error: ${(sttState as SttState.Error).reason}"
            }
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = transcript,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Row {
            Button(onClick = { viewModel.startWorkflow() }) {
                Text("Start")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { viewModel.// TODO: remove Redux
dispatch: // TODO: remove Redux
dispatch: // TODO: remove Redux dispatch: dispatch(SttEvent.StopListening) }) {
                Text("Stop")
            }
        }
    }
}
