package com.sildeag.sound2text.core.pdf.forms

interface PdfFormDiscovery {
    suspend fun discoverForms(basePath: String):
            List<PdfFormDescriptor>
}