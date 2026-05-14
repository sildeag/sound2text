package com.sildeag.sound2text.core.common.logging

class ModuleLogger(
    private val moduleName: String,
    private val delegate: Logger
) : Logger {
    fun Logger.log(level: LogLevel, message: String, throwable: Throwable?) {
        delegate.log(level, "[$moduleName] $message", throwable)
    }

    override fun debug(message: String) {
        TODO("Not yet implemented")
    }

    override fun info(message: String) {
        TODO("Not yet implemented")
    }

    override fun error(message: String, throwable: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun warning(message: String) {
        TODO("Not yet implemented")
    }

    override fun severe(message: String, throwable: Throwable?) {
        TODO("Not yet implemented")
    }
}
