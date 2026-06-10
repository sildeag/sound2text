package com.sildeag.sound2text.di.pdf

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.pdf.PdfPluginRegistry
import com.sildeag.sound2text.core.pdf.PdfProcessor
import com.sildeag.sound2text.pdf.itext.ITextPdfProcessor
import com.sildeag.sound2text.pdf.pdfbox.PdfBoxProcessor
import com.sildeag.sound2text.ui.pdf.PdfViewModel
import org.koin.dsl.module

val pdfModule = module {

    // 1. Plugin discovery (your original code)
    single {
        PdfPluginRegistry.allDiscoveries()
    }

    // 2. PDF processor selection (new unified AppSettings logic)
    factory<PdfProcessor> {
        val settings = get<AppSettings>()
        when (settings.selectedPdfProcessor ?: "itext") {
            "itext" -> ITextPdfProcessor()
            "pdfbox" -> PdfBoxProcessor()
            else -> ITextPdfProcessor()
        }
    }

    // 3. PdfViewModel (depends on processor + settings)
    factory {
        PdfViewModel(
            pdfProcessor = get(),
            settingsStore = get()
        )
    }
}

