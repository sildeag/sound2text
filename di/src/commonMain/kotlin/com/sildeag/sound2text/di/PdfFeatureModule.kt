package com.sildeag.sound2text.di

import org.koin.dsl.module

val pdfFeatureModule = module {
    factory {
        PdfFeatureViewModel(
            processorSelector = get(),
            renderer = get(),
            extractor = get(),
            resources = get(),
            dispatchers = get(),
            logger = get()
        )
    }
}
