package com.sildeag.sound2text.pdfdesktop.itext

import com.sildeag.sound2text.core.pdf.*
class ITextFormEngineFactory : PdfFormEngineFactory {
    override fun load(descriptor: PdfFormDescriptor): PdfFormEngine =
        ITextFormEngine(descriptor)
}
