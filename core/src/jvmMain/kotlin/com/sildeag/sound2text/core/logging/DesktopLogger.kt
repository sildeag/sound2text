package com.sildeag.sound2text.core.logging

class DesktopLogger : Logger {
    private fun out(level: String, message: String, throwable: Throwable?) {
        throwable?.printStackTrace()
    }
    override fun log(
        level: LogLevel,
        message: String,
        throwable: Throwable?
    ) {
        println("[$level] $message")
    }
    override fun debug(message: String) =
        out("DEBUG", message, null)
    override fun info(message: String) =
        out("INFO", message, null)
    override fun warning(message: String) =
        out("WARN", message, null)
    override fun error(message: String, throwable: Throwable?) =
        out("ERROR", message, throwable)
    override fun severe(message: String, throwable: Throwable?) =
        out("SEVERE", message, throwable)
}
