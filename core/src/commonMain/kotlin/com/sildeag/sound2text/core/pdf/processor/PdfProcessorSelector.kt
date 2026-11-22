package com.sildeag.sound2text.core.pdf.processor

class PdfProcessorSelector(
    private val itext: PdfProcessor?,
    private val pdfbox: PdfProcessor?,
    private val unified: PdfProcessor
) {
    fun select(engineKey: String): PdfProcessor = when (engineKey.lowercase()) {
        "itext" -> itext ?: unified
        "pdfbox" -> pdfbox ?: unified
        "unified" -> unified
        else -> unified
    }
}