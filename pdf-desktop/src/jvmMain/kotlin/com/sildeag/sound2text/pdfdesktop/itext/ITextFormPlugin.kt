package com.sildeag.sound2text.pdfdesktop.itext

import pdf.PdfFormDescriptor
import pdf.PdfFormEngineFactory
import pdf.PdfFormPlugin

class ITextFormPlugin : PdfFormPlugin {
    override val engineName: String = "itext"
    override fun createFactory(): PdfFormEngineFactory =
        ITextFormEngineFactory()
    override fun discoverForms(basePath: String):
            List<PdfFormDescriptor> =
        ITextFormDiscovery().discoverForms(basePath)
}
