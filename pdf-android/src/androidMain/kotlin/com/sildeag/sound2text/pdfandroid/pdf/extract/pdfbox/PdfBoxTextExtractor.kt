package com.sildeag.sound2text.pdfandroid.pdf.extract.pdfbox

import com.sildeag.sound2text.core.pdf.extract.PdfTextExtractor
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper

import java.io.File

class PdfBoxTextExtractor : PdfTextExtractor {
    // Optimized File-based extraction
    override suspend fun extractText(file: File): List<String> {
        return PDDocument.load(file).use { document ->
            val stripper = PDFTextStripper()
            (1..document.numberOfPages).map { pageNum ->
                stripper.startPage = pageNum
                stripper.endPage = pageNum
                stripper.getText(document).trim()
            }
        }
    }
    // ByteArray overload (if keeping support for in-memory streams)
    override suspend fun extractText(bytes: ByteArray): List<String>
    {
        return PDDocument.load(bytes).use { document ->
            val stripper = PDFTextStripper()
            (1..document.numberOfPages).map { pageNum ->
                stripper.startPage = pageNum
                stripper.endPage = pageNum
                stripper.getText(document).trim()
            }
        }
    }
}
