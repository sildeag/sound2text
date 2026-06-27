package com.sildeag.sound2text.di

import com.sildeag.sound2text.core.capabilities.DeviceCapabilityProvider
import com.sildeag.sound2text.core.capabilities.getPlatformCapabilityProvider
import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.core.config.FeatureFlags
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.core.settings.SettingsStore
import org.koin.dsl.module

val coreModule = module {
    // Logging
    single<Logger> { GetPlatformLogger() }
    // Settings
    single<SettingsStore> { getPlatformSettingsStore() }
    // Capabilities
    single<DeviceCapabilityProvider>
    { getPlatformCapabilityProvider() }
    // Environment / config
    single { AppSettings(get()) }
    single { Environment(get()) }
    single { FeatureFlags(get()) }
}

