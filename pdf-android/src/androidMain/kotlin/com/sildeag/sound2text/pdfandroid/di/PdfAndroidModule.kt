package com.sildeag.sound2text.pdfandroid.di

import com.sildeag.sound2text.core.pdf.UnifiedFormRegistry
import org.koin.dsl.module
val PdfAndroidModule = module {
    // No Android PDF plugins yet
    single {
        UnifiedFormRegistry(
            plugins = emptyList()
        )
    }
}
