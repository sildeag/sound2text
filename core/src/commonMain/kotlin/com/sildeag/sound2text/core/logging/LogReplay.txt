package com.sildeag.sound2text.core.logging

class LogReplay(private val entries: List<String>) {
    fun filterByTag(tag: String): List<String> =
        entries.filter { it.contains("[$tag]") }
    fun filterByLevel(level: LogLevel): List<String> =
        entries.filter { it.startsWith("[${level.name}]") }
    fun filterByKeyword(keyword: String): List<String> =
        entries.filter { it.contains(keyword, ignoreCase = true) }
    fun replay(onEntry: (String) -> Unit) {
        entries.forEach { onEntry(it) }
    }
}
