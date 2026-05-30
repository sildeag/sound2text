package com.sildeag.sound2text.core.logging

import com.sildeag.sound2text.core.config.LogLevel

enum class LogLevel {
    DEBUG, INFO, WARN, ERROR, SEVERE

    companion object {
        val Info: LogLevel
    }
}
