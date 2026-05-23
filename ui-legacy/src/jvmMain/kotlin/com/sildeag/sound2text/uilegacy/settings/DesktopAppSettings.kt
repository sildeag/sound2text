package com.sildeag.sound2text.uilegacy.settings

import com.sildeag.sound2text.core.config.AppSettings
import java.util.prefs.Preferences

class DesktopAppSettings(private val base: AppSettings) {
    private val prefs = Preferences.userRoot().node("sound2text")

    fun getString(key: String, default: String): String =
        prefs.get(key, default)

    fun putString(key: String, value: String) {
        prefs.put(key, value)
    }
}


