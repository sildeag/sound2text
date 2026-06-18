package com.sildeag.sound2text.pdfdesktop.itext

import pdf.PdfFormDescriptor
import pdf.PdfFormEngine
import pdf.PdfFormEngineFactory

class ITextFormEngineFactory : PdfFormEngineFactory {
    override fun load(descriptor: PdfFormDescriptor): PdfFormEngine =
        ITextFormEngine(descriptor)
}
