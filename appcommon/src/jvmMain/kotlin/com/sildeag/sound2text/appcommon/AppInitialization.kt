package com.sildeag.sound2text.appcommon

import com.sildeag.sound2text.appcommon.classLoader
import com.sildeag.sound2text.appcommon.masterDiModule
import com.sildeag.sound2text.di.FeatureModuleProvider
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import java.util.ServiceLoader

actual fun initKoin(vararg platformModules: Module) {

    val loader =
        ServiceLoader.load(FeatureModuleProvider::class.java,
            classLoader
        )
    val providers = loader.toList()
    println("=== KOIN DEBUG: Found ${providers.size}FeatureModuleProviders ===")
            providers.forEach { println(" -> Provider: ${it::class.java.name}, features: ${it.module}") }

    startKoin {
        modules(
            listOf(masterDiModule) +
            platformModules.toList()
        )
    }
}