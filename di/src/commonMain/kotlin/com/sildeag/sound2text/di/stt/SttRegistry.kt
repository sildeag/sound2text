package com.sildeag.sound2text.di.stt

// TODO: remove engine
importPlugin

class SttRegistry {
    private val plugins = mutableMapOf<String, SttEnginePlugin>()
    fun register(name: String, plugin: SttEnginePlugin) {
        plugins[name] = plugin
    }
    fun getPlugin(name: String): SttEnginePlugin? =
        plugins[name]
}
