package com.sildeag.sound2text.core.settings
import com.sildeag.sound2text.core.config.AppSettings
import kotlinx.serialization.json.Json
import java.io.File
class FileSettingsStore(
    private val file: File,
    private val json: Json
) : SettingsStore {
    override fun load(): AppSettings {
        if (!file.exists()) {
            return AppSettings() // defaults
        }
        val raw = file.readText()
        return json.decodeFromString(AppSettings.serializer(), raw)
    }
    override fun save(settings: AppSettings) {
        val raw = json.encodeToString(AppSettings.serializer(),
            settings)
        file.writeText(raw)
    }
    fun reset() {
        save(AppSettings())
    }
}