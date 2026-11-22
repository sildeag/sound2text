package com.sildeag.sound2text.pdfandroid.capabilities

import android.content.Context
import android.content.pm.PackageManager
import com.sildeag.sound2text.core.capabilities.DeviceCapabilities
import com.sildeag.sound2text.core.capabilities.DeviceCapabilityProvider

class AndroidDeviceCapabilityProvider(
    private val context: Context
) : DeviceCapabilityProvider {
    override fun detect(): DeviceCapabilities {
        return DeviceCapabilities(
            hasMicrophone =
                context.packageManager.hasSystemFeature(
                    PackageManager.FEATURE_MICROPHONE
                ),
            isOfflineCapable = true,
            hasPulseLogic = true
        )
    }
}
