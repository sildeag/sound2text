package com.sildeag.sound2text.core.common.logging

class ConsoleLogger : Logger {
    override fun debug(msg: String) = println("[INFO] $msg")
    override fun info(msg: String) = println("[INFO] $msg")
    override fun warning(msg: String) = println("[WARN] $msg")
    override fun error(msg: String, throwable: Throwable?) = println("[ERROR] $msg")
    override fun severe(msg: String, throwable: Throwable?) = println("[SEVERE] $msg")
}