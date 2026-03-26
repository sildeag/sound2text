package com.sildeag.sound2text.uidesktop.settings

import com.sildeag.sound2text.pdf.AppSettings
import java.util.prefs.Preferences
class DesktopAppSettings : AppSettings {
    private val prefs = Preferences.userRoot().node("sound2text")
    override fun getString(key: String, default: String): String =
        prefs.get(key, default)
    override fun putString(key: String, value: String) {
        prefs.put(key, value)
    }
}
