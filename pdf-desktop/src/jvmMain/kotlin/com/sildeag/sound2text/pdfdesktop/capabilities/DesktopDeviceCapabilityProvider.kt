package com.sildeag.sound2text.pdfdesktop.capabilities



class DesktopDeviceCapabilityProvider : DeviceCapabilityProvider {
    override fun detect(): DeviceCapabilities {
        return DeviceCapabilities(
            hasMicrophone = true,
            isOfflineCapable = true,
            hasPulseLogic = true
        )
    }
}