package com.sildeag.sound2text.core.logging

class LogCollector : Logger {
    private val _entries = mutableListOf<String>()
    val entries: List<String> get() = _entries
    private fun add(level: String, message: String, throwable: Throwable?) {
        _entries += "[$level] $message"
        throwable?.let { _entries += "EX: ${it.message}" }
    }
    override fun debug(message: String) =
        add("DEBUG", message, null)
    override fun info(message: String) =
        add("INFO", message, null)
    override fun warning(message: String) =
        add("WARN", message, null)
    override fun error(message: String, throwable: Throwable?) =
        add("ERROR", message, throwable)
    override fun severe(message: String, throwable: Throwable?) =
        add("SEVERE", message, throwable)
    fun clear() = _entries.clear()
}

