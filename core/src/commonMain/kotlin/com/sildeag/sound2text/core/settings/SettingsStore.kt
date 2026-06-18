package com.sildeag.sound2text.core.settings

import com.sildeag.sound2text.core.config.AppSettings

interface SettingsStore {
    fun load(): AppSettings
    fun save(settings: AppSettings)
}

private fun migrate(settings: AppSettings): AppSettings {
    return when (settings.version) {
        1 -> settings // current version
        else -> settings // fallback
    }
}
