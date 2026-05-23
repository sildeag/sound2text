package com.sildeag.sound2text.core.capabilities

data class DeviceCapabilities(
    val hasMicrophone: Boolean,
    val isOfflineCapable: Boolean,
    val hasPulseLogic: Boolean
)
