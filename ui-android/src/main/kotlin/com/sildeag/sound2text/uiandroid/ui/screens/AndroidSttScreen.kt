package com.sildeag.sound2text.android.ui.screens
import androidx.compose.runtime.*
import androidx.compose.material.*
import org.koin.androidx.compose.getViewModel
import com.sildeag.sound2text.android.viewmodel.AndroidSttViewModel
@Composable
fun AndroidSttScreen(
    vm: AndroidSttViewModel = getViewModel()
) {
    val text by vm.text.collectAsState()
    val listening by vm.pulse.listening.collectAsState()
    Column {
        Text(text)
        Button(onClick = { vm.toggle() }) {
            Text(if (listening) "Stop" else "Start")
        }
    }
}
