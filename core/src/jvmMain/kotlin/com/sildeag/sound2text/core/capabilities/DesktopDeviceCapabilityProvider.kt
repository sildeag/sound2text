package com.sildeag.sound2text.core.capabilities

class DesktopDeviceCapabilityProvider : DeviceCapabilityProvider {
    override fun detect(): DeviceCapabilities {
        return DeviceCapabilities(
            hasMicrophone = true,
            isOfflineCapable = true,
            hasPulseLogic = true
        )
    }
}