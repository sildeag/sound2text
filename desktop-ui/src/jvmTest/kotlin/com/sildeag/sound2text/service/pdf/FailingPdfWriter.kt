package com.sildeag.sound2text.service.pdf

class FailingPdfWriter : PdfWriterFactory {
    override fun write(content: String, outputPath: String) {
        // Simulate failure without writing
        println("Simulated failure: PDF not written to $outputPath")
        throw Exception("PDF generation failed")
    }
}
