package com.sildeag.sound2text.core.pdf

interface PdfFormDiscovery {
    /**
     * Scans the given base path for PDF forms supported by this
    engine.
     * Returns a list of PdfFormDescriptor objects.
     */
    fun discoverForms(basePath: String): List<PdfFormDescriptor>
}