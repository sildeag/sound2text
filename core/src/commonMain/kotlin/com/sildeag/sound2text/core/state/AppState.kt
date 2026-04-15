package com.sildeag.sound2text.core.state

import com.sildeag.sound2text.core.stt.ModelDescriptor

data class AppState(
    val selectedEngine: String?,
    val selectedModel: ModelDescriptor?,
    val pdfForm: PdfFormDescriptor?,
    val language: String,
    val isRecording: Boolean,
    val lastResult: String?
)
