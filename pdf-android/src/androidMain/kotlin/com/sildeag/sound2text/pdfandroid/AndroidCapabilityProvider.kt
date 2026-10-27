package com.sildeag.sound2text.core.capabilities

import org.koin.core.component.KoinComponent

object AndroidCapabilityProviderResolver : KoinComponent

actual fun getPlatformCapabilityProvider(): DeviceCapabilityProvider =
    AndroidCapabilityProviderResolver.get()


