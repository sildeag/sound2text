package com.sildeag.sound2text.appcommon

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.sildeag.sound2text.appcommon.classLoader
import com.sildeag.sound2text.di.FeatureModuleProvider

fun main() {
    val serviceUrls = classLoader.getResources("META-INF/services/${FeatureModuleProvider::class.java.name}").toList()
    println("=== KOIN DEBUG: META-INF URLs found: $serviceUrls ===")
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "sound2text"
        ) {
            App()
        }
    }
}
