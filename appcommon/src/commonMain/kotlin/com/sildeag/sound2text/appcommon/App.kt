package com.sildeag.sound2text.appcommon

import androidx.compose.runtime.Composable
import com.sildeag.sound2text.di.FeatureRegistry
import com.sildeag.sound2text.uicommon.MainDashboardScreen
import org.koin.compose.koinInject

@Composable
fun App() {
    // koinInject works across Android, JVM/Desktop, iOS, WASM
    val featureRegistry: FeatureRegistry = koinInject()
    MainDashboardScreen(featureRegistry = featureRegistry)
}


/*
import androidx.compose.runtime.Composable
import com.sildeag.sound2text.di.FeatureRegistry
import com.sildeag.sound2text.uicommon.MainDashboardScreen
import org.koin.compose.KoinContext
import org.koin.java.KoinJavaComponent.get
@Composable
fun App() {
    // Initialize Koin context for Compose
    // Retrieve the registry dynamically resolved via DI
    val featureRegistry: FeatureRegistry = get(FeatureRegistry::class.java)
    MainDashboardScreen(featureRegistry = featureRegistry)
}

 */