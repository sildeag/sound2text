package com.sildeag.sound2text.core.settings

import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.storage.StorageProvider
import kotlinx.serialization.json.Json

class SettingsLoader(
    private val storage: StorageProvider,
    private val json: Json
) {
    private val fileName = "settings.json"
    suspend fun load(): AppSettings? {
        val bytes = storage.read(fileName) ?: return null
        return json.decodeFromString(bytes.decodeToString())
    }
    suspend fun save(settings: AppSettings) {
        val raw = json.encodeToString(settings)
        storage.write(fileName, raw.encodeToByteArray())
    }
}
