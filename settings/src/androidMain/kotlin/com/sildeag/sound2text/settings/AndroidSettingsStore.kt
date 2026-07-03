package com.sildeag.sound2text.settings

import android.content.SharedPreferences
import com.sildeag.sound2text.core.config.AppSettings
import kotlinx.serialization.json.Json

class AndroidSettingsStore(
    private val prefs: SharedPreferences
) : SettingsStore {
    private val json = Json { ignoreUnknownKeys = true }
    override fun load(): AppSettings {
        val raw = prefs.getString("app_settings", null)
        return if (raw == null) {
            AppSettings()
        } else {
            json.decodeFromString(raw)
        }
    }
    override fun save(settings: AppSettings) {
        val raw = json.encodeToString(settings)
        prefs.edit().putString("app_settings", raw).apply()
    }
}