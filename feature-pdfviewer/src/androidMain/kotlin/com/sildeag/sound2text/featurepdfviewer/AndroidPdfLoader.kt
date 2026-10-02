package com.sildeag.sound2text.featurepdfviewer

import com.sildeag.sound2text.core.pdf.forms.PdfFormDiscovery

import com.sildeag.sound2text.core.pdf.forms.PdfFormDescriptor
import com.sildeag.sound2text.featurepdfviewer.loader.PdfFormLoader

class AndroidPdfFormLoader(
    private val discovery: PdfFormDiscovery,
    private val basePath: String // e.g. "forms"
) : PdfFormLoader {
    override suspend fun discoverForms(): List<PdfFormDescriptor> {
        return discovery.discoverForms(basePath)
    }
}
