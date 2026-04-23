package com.sildeag.sound2text.core.logging

interface Logger {
    fun debug(message: String)
    fun info(message: String)
    fun error(message: String, throwable: Throwable? = null)
    fun warning(message: String)
    fun severe(message: String, throwable: Throwable? = null)
    fun log(level: LogLevel, message: String, throwable: Throwable?)
}
