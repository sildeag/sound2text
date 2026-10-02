package com.sildeag.sound2text.featurepdfviewer

import com.sildeag.sound2text.core.pdf.forms.PdfFormDescriptor
import com.sildeag.sound2text.core.pdf.forms.PdfFormDiscovery
import com.sildeag.sound2text.featurepdfviewer.loader.PdfFormLoader

class DesktopPdfFormLoader(
    private val discovery: PdfFormDiscovery,
    private val basePath: String // e.g. user directory
) : PdfFormLoader {
    override suspend fun discoverForms(): List<PdfFormDescriptor> {
        return discovery.discoverForms(basePath)
    }
}