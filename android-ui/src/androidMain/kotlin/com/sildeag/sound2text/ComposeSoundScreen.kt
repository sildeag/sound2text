package com.sildeag.sound2text

import com.sildeag.sound2text.ui.SoundViewModel

@Composable
fun SoundScreen(viewModel: SoundViewModel = hiltViewModel()) {
    val noteText by viewModel.noteText.collectAsState()
    val isListening by viewModel.isListening.collectAsState()
    val statusMessage by viewModel.statusMessage.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Sound2Text",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = noteText,
            onValueChange = { viewModel.onNoteChanged(it) },
            label = { Text("Your note") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Text(
            text = statusMessage,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { viewModel.toggleListening() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isListening) Color.Red else
                        MaterialTheme.colorScheme.primary
                )
            ) {
                Text(if (isListening) "Stop Listening" else "Start Listening")
            }
            Button(onClick = { viewModel.saveToPdf() }) {
                Text("Save to PDF")
            }
        }
    }
}