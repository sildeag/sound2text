package com.sildeag.sound2text.core.config

enum class BuildConfig(val isDebug: Boolean) {
    Debug(true),
    Release(false);

    companion object {
        // Assigned by platform code at startup
        lateinit var current: BuildConfig
        val isDebug: Boolean get() = current.isDebug
    }
}
