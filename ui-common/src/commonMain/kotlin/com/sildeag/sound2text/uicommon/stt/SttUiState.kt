package com.sildeag.sound2text.uicommon.stt

data class SttUiState(
    val lifecycle: SttUiLifecycle = SttUiLifecycle.Idle,
    val partialText: String = "",
    val finalText: String = "",
    val isSaving: Boolean = false,
    val errorMessage: String? = null
)
