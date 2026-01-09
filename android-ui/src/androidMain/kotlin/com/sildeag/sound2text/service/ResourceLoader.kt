package com.sildeag.sound2text.service

import java.io.InputStream
import java.net.URL

interface ResourceLoader {
    fun loadURL(path: String): URL?
    fun loadRelative(clazz: Class<*>, relativePath: String): URL?
    fun loadAsStream(path: String): InputStream?
    fun readTextFromResource(path: String): String?
}
