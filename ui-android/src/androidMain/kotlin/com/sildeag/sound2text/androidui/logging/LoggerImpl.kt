package com.sildeag.sound2text.logging

class LoggerImpl : Logger {

    override fun log(message: String) {
        println(" LOG: $message") // ℹ ️
    }

    override fun warning(message: String) {
        println(" WARNING: $message") // ⚠️
    }

    override fun error(message: String, throwable: Throwable?) {
        println(" ERROR: $message") // ❌
        throwable?.printStackTrace()
    }

    override fun severe(message: String, throwable: Throwable?) {
        println(" SEVERE: $message") // 🔥
        throwable?.printStackTrace()
    }


}

