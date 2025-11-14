package com.sildeag.sound2text.logging

interface Logger {
    fun log(message: String)
    fun error(message: String, throwable: Throwable? = null)
    fun warning(message: String)
    fun severe(message: String, throwable: Throwable? = null)
}
