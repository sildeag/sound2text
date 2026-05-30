package com.sildeag.sound2text.core.capabilities

actual fun getPlatformCapabilityProvider(): DeviceCapabilityProvider =
    org.koin.java.KoinJavaComponent.getKoin().get()
