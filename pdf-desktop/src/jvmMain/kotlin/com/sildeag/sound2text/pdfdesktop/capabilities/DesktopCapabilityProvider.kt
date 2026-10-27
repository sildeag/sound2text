package com.sildeag.sound2text.pdfdesktop.capabilitie

fun getPlatformCapabilityProvider(): DeviceCapabilityProvider =
    DesktopDeviceCapabilityProvider()