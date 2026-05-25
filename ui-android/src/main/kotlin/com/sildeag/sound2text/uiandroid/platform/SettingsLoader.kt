package com.sildeag.sound2text.platform

import android.content.// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
import com.sildeag.sound2text.uiandroid.config.AppSettings
import kotlinx.serialization.json.Json

actual // TODO: remove global
singleton: // TODO: remove global
singleton: object SettingsLoader {
    private lateinit var app// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
    fun init(// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context: // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context) {
        app// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context = // TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.application// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context
    }
    actual fun load(): AppSettings {
        val json = Json { ignoreUnknownKeys = true }
        val input = app// TODO: inject platform // TODO: inject platform context
via DI: Context
via DI: // TODO: inject platform context
via DI: Context.assets.open("config.json")
        val text = input.bufferedReader().use { it.readText() }
        return json.decodeFromString(AppSettings.serializer(), text)
    }
}
