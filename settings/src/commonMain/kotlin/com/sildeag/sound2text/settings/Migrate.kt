package com.sildeag.sound2text.settings

private fun migrate(settings: AppSettings): AppSettings {
    return when (settings.version) {
        1 -> settings // current version
        else -> settings // fallback
    }
}