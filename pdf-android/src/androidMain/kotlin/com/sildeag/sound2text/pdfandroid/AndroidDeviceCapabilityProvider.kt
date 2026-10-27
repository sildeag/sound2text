package com.sildeag.sound2text.core.capabilities

import android.content.Context
import android.content.pm.PackageManager

class AndroidDeviceCapabilityProvider(
    private val context: Context
) : DeviceCapabilityProvider {
    override fun detect(): DeviceCapabilities {
        return DeviceCapabilities(
            hasMicrophone =
                context.packageManager.hasSystemFeature(
                    PackageManager.FEATURE_MICROPHONE),
            isOfflineCapable = true,
            hasPulseLogic = true
        )
    }
}
