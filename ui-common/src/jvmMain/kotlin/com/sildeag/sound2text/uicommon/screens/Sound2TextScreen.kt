package com.sildeag.sound2text.uicommon.ui

import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.uicommon.state.SttAction
import com.sildeag.sound2text.uicommon.viewmodel.dispatch
import com.sildeag.sound2text.uicommon.viewmodel.CoreSoundViewModel
import com.sildeag.sound2text.uicommon.state.SttState


@Composable
fun Sound2TextScreen(viewModel: CoreSoundViewModel) {
    val sttState by viewModel.state.collectAsState()
    val transcript by viewModel.transcript.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = when (val s = sttState) {
                SttState.Idle -> "Idle"
                SttState.Recording -> "Recording..."
                SttState.Processing -> "Processing..."
                SttState.Finished -> "Finished"
                is SttState.Error -> "Error: ${s.message}"
            }


        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = transcript,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Row {
            Button(onClick = { viewModel.// TODO: remove Redux
dispatch: // TODO: remove Redux
dispatch: // TODO: remove Redux dispatch: dispatch(SttAction.Start) }) {
                Text("Start")
            }

            Spacer(Modifier.width(8.dp))
            Button(onClick =
                { viewModel.stopRecording(SttAction.Stop) }) {
                Text("Stop")
            }
        }
    }
}


