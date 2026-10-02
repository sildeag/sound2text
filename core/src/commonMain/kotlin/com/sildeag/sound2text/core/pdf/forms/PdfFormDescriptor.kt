package com.sildeag.sound2text.core.pdf.forms

data class PdfFormDescriptor(
    val formName: String,
    val path: String,
    val fields: List<PdfFormField>
)
