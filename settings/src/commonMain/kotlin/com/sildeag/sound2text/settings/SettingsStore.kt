package com.sildeag.sound2text.settings

import com.sildeag.sound2text.core.config.AppSettings

interface SettingsStore {
    fun load(): AppSettings
    fun save(settings: AppSettings)
}


