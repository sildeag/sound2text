package com.sildeag.sound2text.core.pdf

data class PdfFormDescriptor(
    val engine: String,
    val formName: String,
    val path: String,
    val fields: List<PdfFieldDescriptor>
)

