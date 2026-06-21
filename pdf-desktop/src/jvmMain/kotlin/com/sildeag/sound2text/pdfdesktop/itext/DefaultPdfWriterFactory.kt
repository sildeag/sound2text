package com.sildeag.sound2text.pdfdesktop.itext

import com.itextpdf.kernel.pdf.PdfWriter
import java.io.File
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.sildeag.sound2text.core.pdf.PdfWriterFactory
class DefaultPdfWriterFactory : PdfWriterFactory {
    override fun write(content: String, outputPath: String) {
        val writer = PdfWriter(File(outputPath))
        val pdfDoc = PdfDocument(writer)
        val document = Document(pdfDoc)
        document.add(Paragraph(content))
        document.close()
    }
}