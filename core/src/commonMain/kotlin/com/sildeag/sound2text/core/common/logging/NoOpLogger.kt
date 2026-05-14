package com.sildeag.sound2text.core.common.logging

class NoOpLogger : Logger {
    override fun info(msg: String) {}
    override fun error(message: String, throwable: Throwable?) {}
    override fun warning(message: String) {}
    override fun severe(message: String, throwable: Throwable?) {}
}
