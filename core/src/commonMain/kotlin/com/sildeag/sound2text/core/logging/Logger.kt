package com.sildeag.sound2text.core.logging

interface Logger {
    fun d(tag: String, message: String)
    fun e(tag: String, message: String, throwable: Throwable? = null)
}

class MessageLogger : Logger {
    override fun d(tag: String, message: String) = println("[$tag] $message")
    override fun e(tag: String, message: String, throwable:
    Throwable?) {
        println("ERROR: [$tag] $message")
        throwable?.printStackTrace()
    }
}
