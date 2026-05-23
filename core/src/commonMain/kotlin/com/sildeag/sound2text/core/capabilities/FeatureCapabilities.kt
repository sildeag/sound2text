package com.sildeag.sound2text.core.capabilities

interface FeatureCapabilities {
    val requiresMicrophone: Boolean
    val supportsOfflineMode: Boolean
    val needsPulseLogic: Boolean
}
