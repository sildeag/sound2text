package com.sildeag.sound2text.pdfdesktop.capabilities

import com.sildeag.sound2text.core.capabilities.DeviceCapabilities
import com.sildeag.sound2text.core.capabilities.DeviceCapabilityProvider


class DesktopDeviceCapabilityProvider : DeviceCapabilityProvider {
    override fun detect(): DeviceCapabilities {
        return DeviceCapabilities(
            hasMicrophone = true,
            isOfflineCapable = true,
            hasPulseLogic = true
        )
    }
}