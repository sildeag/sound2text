package com.sildeag.sound2text.pdfdesktop.itext

import com.sildeag.sound2text.core.pdf.*
class ITextFormPlugin : PdfFormPlugin {
    override val engineName: String = "itext"
    override fun createFactory(): PdfFormEngineFactory =
        ITextFormEngineFactory()
    override fun discoverForms(basePath: String):
            List<PdfFormDescriptor> =
        ITextFormDiscovery().discoverForms(basePath)
}