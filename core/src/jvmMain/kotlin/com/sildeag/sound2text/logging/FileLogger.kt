package com.sildeag.sound2text.logging

import java.io.File

class FileLogger(private val file: File) : Logger {
    override fun info(msg: String) = file.appendText("[INFO] $msg\n")
    override fun warning(msg: String) = file.appendText("[WARN] $msg\n")
    override fun error(msg: String, throwable: Throwable?) = file.appendText("[ERROR] $msg\n")
    override fun severe(msg: String, throwable: Throwable?) = file.appendText("[ERROR] $msg\n")
}
