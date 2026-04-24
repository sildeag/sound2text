package com.sildeag.sound2text.core.pdf

data class PdfFieldDescriptor(
    val name: String,
    val type: FieldType,
    val voiceEnabled: Boolean,
    val language: String? = null
)