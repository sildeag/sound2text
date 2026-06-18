package com.sildeag.sound2text.core.state

data class AppState(
    val selectedEngine: String?,
    val selectedModel: ModelDescriptor?,
    val pdfForm: PdfFormDescriptor?,
    val language: String,
    val isRecording: Boolean,
    val lastResult: String?
)
