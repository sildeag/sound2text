package com.sildeag.sound2text.service.pdf

interface PdfWriterFactory {
    fun write(content: String, outputPath: String)
}

