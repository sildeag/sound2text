package com.sildeag.sound2text.di.desktop

import com.sildeag.sound2text.core.pdf.UnifiedFormRegistry
import com.sildeag.sound2text.core.pdf.PdfFormPlugin
import com.sildeag.sound2text.pdfdesktop.itext.ITextFormPlugin
import org.koin.dsl.module
val PdfDesktopModule = module {
    // Register the iText plugin
    single<PdfFormPlugin> { ITextFormPlugin() }
    // Unified registry (can hold multiple plugins)
    single {
        UnifiedFormRegistry(
            plugins = listOf(get<PdfFormPlugin>())
        )
    }
}