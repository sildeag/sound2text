package com.sildeag.sound2text.uiandroid.stt.wizard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.SttModelInfo
@Composable
fun SttWizardScreen(
    viewModel: SttWizardViewModel,
    onDone: (SttConfig) -> Unit
) {
    val state by viewModel.state.collectAsState()
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Choose Engine", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        EngineDropdown(
            engines = state.availableEngines,
            selected = state.selectedEngine,
            onSelect = { viewModel.selectEngine(it) }
        )
        Spacer(Modifier.height(16.dp))
        Text("Choose Model", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        ModelList(
            models = state.availableModels,
            selected = state.selectedModel,
            onSelect = { viewModel.selectModel(it.id) }
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = { onDone(viewModel.buildConfig()) },
            enabled = state.isReady
        ) {
            Text("Continue")
        }
    }
}
@Composable
private fun EngineDropdown(
    engines: List<String>,
    selected: String?,
    onSelect: (String) -> Unit
) {
    var expanded = remember { mutableStateOf(false) }
    Box {
        OutlinedButton(onClick = { expanded.value = true }) {
            Text(selected ?: "Select engine")
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            engines.forEach { engine ->
                DropdownMenuItem(
                    text = { Text(engine) },
                    onClick = {
                        expanded.value = false
                        onSelect(engine)
                    }
                )
            }
        }
    }
}
@Composable
private fun ModelList(
    models: List<SttModelInfo>,
    selected: SttModelInfo?,
    onSelect: (SttModelInfo) -> Unit
) {
    Column {
        models.forEach { model ->
            val isSelected = model.id == selected?.id
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(model) }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = { onSelect(model) }
                )
                Spacer(Modifier.width(8.dp))
                Text(model.displayName)
            }
        }
    }
}
