package com.sildeag.sound2text.pdfandroid.pdf.extract

import com.sildeag.sound2text.core.pdf.extract.PdfTextExtractor
import com.sildeag.sound2text.pdfandroid.pdf.extract.itext.ITextExtractor
import com.sildeag.sound2text.pdfandroid.pdf.extract.pdfbox.PdfBoxTextExtractor
import java.io.File
import java.io.FileNotFoundException

/**
 * Android Default Extractor wrapper implementing a primary ->
fallback resilience chain.
 * Primary: PDFBox-Android (Fast, mobile-optimized)
 * Fallback: iText (Secondary extraction parser)
 */
class AndroidDefaultPdfExtractor(
    private val primaryEngine: PdfTextExtractor =
        PdfBoxTextExtractor(),
    private val fallbackEngine: PdfTextExtractor? =
        ITextExtractor()
) : PdfTextExtractor {
    override suspend fun extractText(file: File): List<String> {
        return try {
            primaryEngine.extractText(file)
        } catch (e: Exception) {
            if (fallbackEngine != null && isRecoverable(e)) {
                fallbackEngine.extractText(file)
            } else {
                throw e
            }
        }
    }
    override suspend fun extractText(bytes: ByteArray): List<String>
    {
        return try {
            primaryEngine.extractText(bytes)
        } catch (e: Exception) {
            if (fallbackEngine != null && isRecoverable(e)) {
                fallbackEngine.extractText(bytes)
            } else {
                throw e
            }
        }
    }
    private fun isRecoverable(e: Exception): Boolean {
        return when (e) {
            is FileNotFoundException,
            is SecurityException -> false
            else -> true
        }
    }
}
