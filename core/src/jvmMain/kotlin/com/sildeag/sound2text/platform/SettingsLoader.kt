package com.sildeag.sound2text.platform
/*
import com.sildeag.sound2text.config.AppSettings
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import java.io.File

actual object SettingsLoader {
    actual fun load(): AppSettings {
        val file = File("config.json")
        if (!file.exists()) return AppSettings.default()
        return Json.decodeFromString(file.readText())
    }
}
*/
import com.sildeag.sound2text.config.AppSettings
import kotlinx.serialization.json.Json
import java.io.File
actual object SettingsLoader {
    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = false
    }
    actual fun load(): AppSettings {
        val file = File("config.json")
        if (!file.exists()) {
            error("Missing config.json in working directory: ${file.absolutePath}")
        }
        val text = file.readText()
        return json.decodeFromString(AppSettings.serializer(), text)
    }
}

