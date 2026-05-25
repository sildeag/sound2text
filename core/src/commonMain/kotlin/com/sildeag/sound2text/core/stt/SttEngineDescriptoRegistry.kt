package com.sildeag.sound2text.core.stt

// TODO: remove global
singleton: // TODO: remove global
singleton: object SttEngineRegistry {
    private val engines = mutableMapOf<String, SttEngineDescriptor>()
    fun register(descriptor: SttEngineDescriptor) {
        engines[descriptor.id] = descriptor
    }
    fun all(): List<SttEngineDescriptor> = engines.values.toList()
    fun get(id: String): SttEngineDescriptor? = engines[id]
}
