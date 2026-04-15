package com.sildeag.sound2text.core.pdf

interface PdfFormPlugin {
    val engineName: String
    fun createFactory(): PdfFormEngineFactory
    fun discoverForms(basePath: String): List<PdfFormDescriptor>
}
