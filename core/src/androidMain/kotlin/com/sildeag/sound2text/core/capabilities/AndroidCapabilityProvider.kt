package com.sildeag.sound2text.core.capabilities

import org.koin.core.component.KoinComponent
import org.koin.core.component.get

object AndroidCapabilityProviderResolver : KoinComponent

actual fun getPlatformCapabilityProvider(): DeviceCapabilityProvider =
    AndroidCapabilityProviderResolver.get()


