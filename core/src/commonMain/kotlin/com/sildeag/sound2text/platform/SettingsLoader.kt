package com.sildeag.sound2text.platform

import com.sildeag.sound2text.pdfdesktop.AppSettings

expect object SettingsLoader {
    fun load(): AppSettings
}
