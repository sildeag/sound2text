package com.sildeag.sound2text.desktopui.compose
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.viewmodel.SoundViewModel
@Composable
fun SoundScreen(viewModel: SoundViewModel) {
    var path by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = path,
            onValueChange = { path = it },
            label = { Text("Path to WAV file") }
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = { viewModel.transcribe(path) }) {
            Text("Transcribe")
        }
        Spacer(Modifier.height(12.dp))
        Text("Result:")
        Text(viewModel.text.collectAsState().value)
        Spacer(Modifier.height(12.dp))
        Button(onClick = { viewModel.save("output") }) {
            Text("Save")
        }
    }
}
