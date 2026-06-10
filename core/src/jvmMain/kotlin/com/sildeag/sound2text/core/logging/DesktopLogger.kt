package com.sildeag.sound2text.core.logging

class DesktopLogger : Logger {
    override fun d(tag: String, message: String) = println("[$tag] $message")
    override fun e(tag: String, message: String, throwable:
    Throwable?) {
        println("ERROR: [$tag] $message")
        throwable?.printStackTrace()
    }
}

