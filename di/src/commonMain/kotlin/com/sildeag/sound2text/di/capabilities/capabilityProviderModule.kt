package com.sildeag.sound2text.di.capabilities

import com.sildeag.sound2text.core.capabilities.DeviceCapabilityProvider
import com.sildeag.sound2text.core.capabilities.getPlatformCapabilityProvider
import org.koin.core.module.Module
import org.koin.dsl.module

val capabilityProviderModule = module {
    single<DeviceCapabilityProvider> { getPlatformCapabilityProvider() }
    single { get<DeviceCapabilityProvider>().detect() }
}