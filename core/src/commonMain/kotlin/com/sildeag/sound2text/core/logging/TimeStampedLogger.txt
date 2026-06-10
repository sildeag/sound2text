package com.sildeag.sound2text.core.logging

import kotlin.time.Clock

class TimestampedLogger(
    private val delegate: Logger
) : Logger {
    private fun stamp(message: String): String {
        val ts = Clock.System.now().toString()
        return "[$ts] $message"
    }
    override fun debug(message: String) =
        delegate.debug(stamp(message))
    override fun info(message: String) =
        delegate.info(stamp(message))
    override fun warning(message: String) =
        delegate.warning(stamp(message))
    override fun error(message: String, throwable: Throwable?) =
        delegate.error(stamp(message), throwable)
    override fun severe(message: String, throwable: Throwable?) =
        delegate.severe(stamp(message), throwable)
}
