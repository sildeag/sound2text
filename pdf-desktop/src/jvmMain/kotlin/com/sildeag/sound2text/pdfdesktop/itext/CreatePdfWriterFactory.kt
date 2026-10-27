package com.sildeag.sound2text.pdfdesktop.itext


import com.sildeag.sound2text.core.pdf.PdfWriterFactory
import com.sildeag.sound2text.core.pdf.createPdfWriterFactory
import pdf.PdfWriterFactory

actual fun createPdfWriterFactory(): PdfWriterFactory =
    DefaultPdfWriterFactory()
