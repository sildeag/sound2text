package com.sildeag.sound2text.platform

import android.content.Context
import com.sildeag.sound2text.config.AppSettings
import kotlinx.serialization.json.Json
actual object SettingsLoader {
    lateinit var context: Context
    actual fun load(): AppSettings {
        val input = context.assets.open("config.json")
        val text = input.bufferedReader().use { it.readText() }
        return Json.decodeFromString(text)
    }
}

/*
import com.sildeag.sound2text.config.AppSettings

actual object `SettingsLoader` {
    actual fun load(): AppSettings {
        val context = androidContext() // or inject via DI
        val jsonText = context.assets.open("config.json")
            .bufferedReader()
            .readText()
        return Json.decodeFromString<AppSettings>(jsonText)
    }
}

 */