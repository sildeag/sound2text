package com.sildeag.sound2text.core.pdf

interface PdfFormEngineFactory {
    fun load(descriptor: PdfFormDescriptor): PdfFormEngine
}