package com.sildeag.sound2text.core.service.note

import com.sildeag.sound2text.core.service.pdf.PdfWriterFactory

class NoteProviderMock(
    private val noteEditor: NoteEditor,
    private val pdfWriterFactory: PdfWriterFactory
) : NoteProvider {
    private val content: String = "test"
    val calls = mutableListOf<Pair<String, String>>()
    override fun generatePDF(filename: String) {
        println("Mock PDF generation: '$content' -> $filename")
        val content = noteEditor.getText()
        calls.add(content to filename)
    }
}
