package com.sildeag.sound2text.core.capabilities

interface DeviceCapabilityProvider {
    fun detect(): DeviceCapabilities
}
