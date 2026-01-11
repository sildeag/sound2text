package com.sildeag.sound2text.config

import kotlinx.serialization.json.Json
import java.io.File

actual object SettingsLoader {
    actual fun load(): AppSettings {
        val jsonText = File("config.json").readText()
        return Json.decodeFromString<AppSettings>(jsonText)
    }
}
