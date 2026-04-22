package com.sildeag.sound2text.core.logging

import kotlin.time.Clock

class TimestampedLogger(
    private val delegate: Logger
) : Logger {
    override fun log(level: LogLevel, message: String, throwable: Throwable?) {
        val ts = Clock.System.now().toString()
        delegate.log(level, "[$ts] $message", throwable)
    }
}