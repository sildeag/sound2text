package com.sildeag.sound2text.core.capabilities

actual fun getPlatformCapabilityProvider(): DeviceCapabilityProvider =
    AndroidDeviceCapabilityProvider(android// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context())
