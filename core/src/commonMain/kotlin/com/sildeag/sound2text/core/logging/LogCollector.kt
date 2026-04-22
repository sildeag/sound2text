package com.sildeag.sound2text.core.logging

class LogCollector : Logger {
    private val _entries = mutableListOf<String>()
    val entries: List<String> get() = _entries
    override fun log(level: LogLevel, message: String, throwable: Throwable?) {
        val entry = "[${level.name}] $message"
        _entries += entry
        throwable?.let { _entries += "EX: ${it.message}" }
    }
    fun clear() = _entries.clear()
}
