package com.sildeag.sound2text.pdfdesktop.itext

import com.sildeag.sound2text.core.pdf.PdfFormDiscovery

actual createPdfFormDiscovery(): PdfFormDiscovery =
    ITextFormDiscovery()