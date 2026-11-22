package com.sildeag.sound2text.appcommon

import com.sildeag.sound2text.di.FeatureModuleProvider
import com.sildeag.sound2text.di.FeatureRegistry
import com.sildeag.sound2text.di.FeatureRegistryImpl
import com.sildeag.sound2text.di.coreModule
import com.sildeag.sound2text.pdfdesktop.di.desktopPdfFormScannerModule
import com.sildeag.sound2text.pdfdesktop.di.desktopPdfModule
import com.sildeag.sound2text.pdfdesktop.di.desktopPdfProcessorModule
import com.sildeag.sound2text.pdfdesktop.di.desktopPdfResourceLoaderModule
import com.sildeag.sound2text.uicommon.di.uiCommonPdfModule

//import com.sildeag.sound2text.feature.notes.di.notesModule
//import com.sildeag.sound2text.feature.recording.di.recordingModule
import org.koin.dsl.module
import java.util.ServiceLoader

val classLoader = FeatureModuleProvider::class.java.classLoader
val serviceName = "META-INF/services/${FeatureModuleProvider::class.java.name}"
val serviceUrls = classLoader?.getResources(serviceName)?.toList() ?: emptyList()

val loadedProviders = run {
    val providers = ServiceLoader.load(FeatureModuleProvider::class.java, classLoader).toList()
    println("==================================================")
    println("=== KMP SERVICE LOADER & KOIN DIAGNOSTIC ===")
    println("Target Service: ${FeatureModuleProvider::class.java.name}")
    println("Service Resource URLs found: $serviceUrls")
    println("Loaded FeatureModuleProviders count: ${providers.size}")
    providers.forEach { provider ->
        println("  -> Provider Class: ${provider::class.java.name}, Module: ${provider.module}")
    }
    println("==================================================")
    providers
}

val discoveredFeatureModules = loadedProviders.map { it.module }
val masterDiModule = module {
    // Include feature-specific modules managed by their respective teams/packages
    includes(coreModule)
    includes(uiCommonPdfModule)
    includes(discoveredFeatureModules)
    includes(desktopPdfModule)
    includes(desktopPdfResourceLoaderModule)
    includes(desktopPdfProcessorModule)
    includes(desktopPdfFormScannerModule)
    // Central registry binding that aggregates all discovered processors
    single<FeatureRegistry> {
        FeatureRegistryImpl(getAll())
    }
}