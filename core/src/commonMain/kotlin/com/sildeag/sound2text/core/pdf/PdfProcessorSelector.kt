package com.sildeag.sound2text.core.pdf

class PdfProcessorSelector(
    private val itext: PdfProcessor?,
    private val pdfbox: PdfProcessor?,
    private val unified: PdfProcessor
) {
    fun select(name: String): PdfProcessor = when (name.lowercase()) {
        "itext" -> itext ?: unified
        "pdfbox" -> pdfbox ?: unified
        "unified" -> unified
        else -> unified
    }
}