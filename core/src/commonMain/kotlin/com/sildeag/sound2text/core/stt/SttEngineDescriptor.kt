package com.sildeag.sound2text.core.stt

interface SttEngineDescriptor {
    val id: String
    val displayName: String
    fun create(config: SttConfig): SttService
}
