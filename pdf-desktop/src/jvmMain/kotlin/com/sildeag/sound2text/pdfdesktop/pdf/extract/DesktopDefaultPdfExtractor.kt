package com.sildeag.sound2text.pdfdesktop.pdf.extract

import com.sildeag.sound2text.pdfdesktop.pdf.extract.itext.ITextExtractor
import com.sildeag.sound2text.core.pdf.extract.PdfTextExtractor
import com.sildeag.sound2text.pdfdesktop.pdf.extract.pdfbox.PdfBoxTextExtractor
import java.io.File
import java.io.FileNotFoundException

/**
 * Desktop Default Extractor wrapper implementing a primary ->
fallback resilience chain.
 * Primary: iText (Heavyweight, robust layout parsing)
 * Fallback: PDFBox (JVM Native fallback)
 */
class DesktopDefaultPdfExtractor(
    private val primaryEngine: PdfTextExtractor =
        ITextExtractor(),
    private val fallbackEngine: PdfTextExtractor? =
        PdfBoxTextExtractor()
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
        // Fast-fail non-recoverable IO issues so we don't waste time retrying
        return when (e) {
            is FileNotFoundException,
            is SecurityException -> false
            else -> true
        }
    }
}