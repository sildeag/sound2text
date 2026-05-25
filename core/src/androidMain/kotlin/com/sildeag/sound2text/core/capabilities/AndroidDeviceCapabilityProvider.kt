package com.sildeag.sound2text.core.capabilities

import android.content.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import android.content.pm.PackageManager

class AndroidDeviceCapabilityProvider(
    private val // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
) : DeviceCapabilityProvider {
    override fun detect(): DeviceCapabilities {
        return DeviceCapabilities(
            hasMicrophone =
// TODO: replace with DI +
ViewModel: FEATURE_MICROPHONE),
            isOfflineCapable = true,
            hasPulseLogic = true
        )
    }
}
