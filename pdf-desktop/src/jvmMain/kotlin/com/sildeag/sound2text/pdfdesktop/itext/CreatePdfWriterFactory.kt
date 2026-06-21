package com.sildeag.sound2text.pdfdesktop.itext


import com.sildeag.sound2text.core.pdf.PdfWriterFactory
import com.sildeag.sound2text.core.pdf.createPdfWriterFactory
actual fun createPdfWriterFactory(): PdfWriterFactory =
    DefaultPdfWriterFactory()
