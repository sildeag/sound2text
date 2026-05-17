package com.sildeag.sound2text.core.pdf

data class PdfFieldDescriptor(
    val name: String,
    val type: PdfFieldType,
    val value: String?,
    val language: String?,
    val voiceEnabled: Boolean
)
