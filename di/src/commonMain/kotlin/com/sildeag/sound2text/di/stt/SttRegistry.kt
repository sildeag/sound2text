package com.sildeag.sound2text.di.stt

import com.sildeag.sound2text.core.stt.SttEnginePlugin

class SttRegistry {
    private val plugins = mutableMapOf<String, SttEnginePlugin>()
    fun register(name: String, plugin: SttEnginePlugin) {
        plugins[name] = plugin
    }
    fun getPlugin(name: String): SttEnginePlugin? =
        plugins[name]
}