package com.sildeag.sound2text.uicommon.wizard

import com.sildeag.sound2text.core.stt.model.SttModelInfo

data class SttWizardState(
    val availableEngines: List<String> = emptyList(),
    val selectedEngine: String? = null,
    val availableModels: List<SttModelInfo> = emptyList(),
    val selectedModel: SttModelInfo? = null,
    val isReady: Boolean = false
)
