package com.sildeag.sound2text.core.capabilities

actual fun getPlatformCapabilityProvider(): DeviceCapabilityProvider =
    AndroidDeviceCapabilityProvider(androidContext())