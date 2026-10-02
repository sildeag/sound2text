package com.sildeag.sound2text.core.pdf.forms

data class PdfFormField(
    val name: String,
    val type: String, // text, checkbox, date, etc.
    val pageIndex: Int,
    val x: Float,
    val y: Float
)

