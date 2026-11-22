package com.sildeag.sound2text.pdfdesktop.capabilities

import com.sildeag.sound2text.core.capabilities.DeviceCapabilityProvider

fun getPlatformCapabilityProvider(): DeviceCapabilityProvider =
    DesktopDeviceCapabilityProvider()