package com.sildeag.sound2text.service

import java.io.IOException
import java.io.InputStream
import java.net.URL
import com.sildeag.sound2text.logging.Logger

class DefaultResourceLoader (
    private val logger: Logger): ResourceLoader {
    override fun loadURL(path: String): URL? =
        DefaultResourceLoader::class.java.getResource(path)
    override fun loadRelative(clazz: Class<*>, relativePath: String): URL? =
        clazz.getResource(relativePath)
    override fun loadAsStream(path: String): InputStream? =
        DefaultResourceLoader::class.java.getResourceAsStream(path)

    override fun readTextFromResource(path: String): String? {
        return try {
            val stream = loadAsStream(path)?.bufferedReader()?.use { it.readText() }
            if (stream == null) {
                logger.warning("Resource not found: $path")
            }
            return stream
        } catch (e: IOException) {
            logger.severe("Failed to load resource '$path': ${e.message}")
            return null
        }
    }
    // Optional helper
    fun load(path: String): InputStream? = loadAsStream(path)

}