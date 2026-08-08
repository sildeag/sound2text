package com.sildeag.sound2text.uicommon.viewmodels

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.di.stt.ModelDiscovery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SttWizardViewModel(
    private val modelDiscovery: ModelDiscovery,
    private val engines: List<String>
) {
    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private val _state = MutableStateFlow(
        SttWizardState(availableEngines = engines)
    )
    val state: StateFlow<SttWizardState> = _state
    fun selectEngine(engineName: String) {
        scope.launch {
            val models = modelDiscovery.listModels(engineName)
            val first = models.firstOrNull()
            _state.value = _state.value.copy(
                selectedEngine = engineName,
                availableModels = models,
                selectedModel = first,
                isReady = first != null
            )
        }
    }
    fun selectModel(modelId: String) {
        val model = _state.value.availableModels.find { it.id == modelId }
        _state.value = _state.value.copy(
            selectedModel = model,
            isReady = model != null
        )
    }
    fun buildConfig(): SttConfig {
        val engine = _state.value.selectedEngine
            ?: error("Engine not selected")
        val model = _state.value.selectedModel
            ?: error("Model not selected")
        return SttConfig(
            engineName = engine,
            modelInfo = model,
            language = model.language,
            extra = model.path?.let { mapOf("modelPath" to it) } ?: emptyMap()
        )
    }
}
