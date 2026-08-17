package com.sildeag.sound2text.core.pdf.extract.itext

import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor
import com.sildeag.sound2text.core.pdf.extract.PdfTextExtractor as CoreExtractor
import java.io.ByteArrayInputStream

class ITextTextExtractor : CoreExtractor {
    override suspend fun extractText(bytes: ByteArray): List<String> {
        val pdf = PdfDocument(PdfReader(ByteArrayInputStream(bytes)))
        val pages = (1..pdf.numberOfPages).map { index ->
            PdfTextExtractor.getTextFromPage(pdf.getPage(index))
        }
        pdf.close()
        return pages
    }
}
