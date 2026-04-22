package com.sildeag.sound2text.core.logging

import kotlin.time.Clock

class TaggedTimestampedLogger(
    private val tag: String,
    private val delegate: Logger
) : Logger {
    override fun log(level: LogLevel, message: String, throwable: Throwable?) {
        val ts = Clock.System.now().toString()
        val tagged = "[$ts][$tag] $message"
        delegate.log(level, tagged, throwable)
    }
}
