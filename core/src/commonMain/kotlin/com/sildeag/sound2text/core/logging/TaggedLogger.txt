package com.sildeag.sound2text.core.logging

class TaggedLogger(
    private val tag: String,
    private val delegate: Logger
) : Logger {
    override fun log(level: LogLevel, message: String, throwable: Throwable?) {
        delegate.log(level, "[$tag] $message", throwable)
    }
}
