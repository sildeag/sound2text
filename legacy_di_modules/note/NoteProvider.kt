package com.sildeag.sound2text.service.note

import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.sildeag.sound2text.logging.Logger
import java.io.File
class NoteProvider(
    private val noteEditor: NoteEditor,
    private val logger: Logger
) {
    fun generatePDF(outputPath: String) {
        try {
            val file = File(outputPath)
            file.parentFile?.mkdirs()
            val writer = PdfWriter(file)
            val pdf = PdfDocument(writer)
            val document = Document(pdf)
            val text = noteEditor.getText()
            document.add(Paragraph(text))
            document.close()
            logger.info("PDF generated at: $outputPath")
        } catch (e: Exception) {
            logger.error("Failed to generate PDF: ${e.message}", e)
        }
    }
}
