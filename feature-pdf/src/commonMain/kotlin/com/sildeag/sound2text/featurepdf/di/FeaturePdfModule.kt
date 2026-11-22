package com.sildeag.sound2text.featurepdf.di

import com.sildeag.sound2text.di.FileProcessorFeature
import com.sildeag.sound2text.featurepdf.FeaturePdfProcessorFeature
import com.sildeag.sound2text.featurepdf.viewmodel.FormDiscoveryViewModel
import com.sildeag.sound2text.featurepdf.viewmodel.PdfFeatureViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val featurePdfModule = module {
    // Register FormDiscoveryViewModel
    single { FormDiscoveryViewModel(get()) }
    // Register PdfFeatureViewModel with required core dependencies
    single {
        PdfFeatureViewModel(
            uiPdfProcessor = get(),
            renderer = get(),
            extractor = get(),
            dispatchers = get(),
            logger = get()
        )
    }
    // 2. Koin module for feature1
    // Qualified binding so it does not collide with feature1Module's FileProcessorFeature
    single<FileProcessorFeature>(named("feature4")) {
        FeaturePdfProcessorFeature(
            discoveryViewModel = get(),
            pdfViewModel = get()
        )
    } bind FileProcessorFeature::class
}
