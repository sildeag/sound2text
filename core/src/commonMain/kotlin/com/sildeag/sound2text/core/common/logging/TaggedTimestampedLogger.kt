package com.sildeag.sound2text.core.common.logging

import kotlin.time.Clock

class TaggedTimestampedLogger(
    private val tag: String,
    private val delegate: Logger
) : Logger {
    private fun format(message: String): String {
        val ts = Clock.System.now().toString()
        return "[$ts][$tag] $message"
    }
    override fun debug(message: String) =
        delegate.debug(format(message))
    override fun info(message: String) =
        delegate.info(format(message))
    override fun warning(message: String) =
        delegate.warning(format(message))
    override fun error(message: String, throwable: Throwable?) =
        delegate.error(format(message), throwable)
    override fun severe(message: String, throwable: Throwable?) =
        delegate.severe(format(message), throwable)
}