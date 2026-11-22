package com.sildeag.sound2text.pdfdesktop.pdf.extract.itext

import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor
import java.io.ByteArrayInputStream
import com.sildeag.sound2text.core.pdf.extract.PdfTextExtractor as CoreExtractor
import java.io.File
class ITextExtractor : CoreExtractor {
    // Optimized File-based extraction
    override suspend fun extractText(file: File): List<String> {
        val pdf = PdfDocument(PdfReader(file))
        val pages = (1..pdf.numberOfPages).map { index ->
            PdfTextExtractor.getTextFromPage(pdf.getPage(index))
        }
        pdf.close()
        return pages
    }
    // ByteArray overload
    override suspend fun extractText(bytes: ByteArray): List<String>
    {
        val pdf =
            PdfDocument(PdfReader(ByteArrayInputStream(bytes)))
        val pages = (1..pdf.numberOfPages).map { index ->
            PdfTextExtractor.getTextFromPage(pdf.getPage(index))
        }
        pdf.close()
        return pages
    }
}

