package com.sildeag.sound2text.service.note

import com.sildeag.sound2text.service.pdf.PdfWriterFactory

class NoteProviderImpl(
    private val noteEditor: NoteEditor,
    private val pdfWriterFactory: PdfWriterFactory
) : NoteProvider {
    override fun generatePDF(outputPath: String)  {
        val content = noteEditor.getText()
        pdfWriterFactory.write(content, outputPath)
    }
}
