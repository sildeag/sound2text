package com.sildeag.sound2text.core.capabilities

fun FeatureCapabilities.isSupportedOn(device: DeviceCapabilities): Boolean {
    if (requiresMicrophone && !device.hasMicrophone) return false
    if (needsPulseLogic && !device.hasPulseLogic) return false
    if (!supportsOfflineMode && !device.isOfflineCapable) return false
    return true
}
