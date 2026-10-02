package com.sildeag.sound2text.core.stt.engine

interface UnifiedEnginePlugin {
    val engineName: String get() = "unified"
    val displayName: String get() = "Unified"

    fun createBackend(): UnifiedBackend
}
