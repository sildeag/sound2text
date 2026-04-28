package com.sildeag.sound2text.di.core.pdf

import org.koin.core.module.Module
import org.koin.dsl.module
val pdfCommonModule: Module = module {
    // If you want a default discovery list via registry:
    single { PdfPluginRegistry.allDiscoveries() }
}
