package com.sildeag.sound2text.service

import com.sildeag.sound2text.logging.Logger
//import sun.tools.jconsole.inspector.Utils.getClass
import java.io.IOException
import java.io.InputStream
import java.net.URL

class ResourceLoaderImpl (
    private val logger: Logger) : ResourceLoader {
    /*
     * Loads a resource from the classpath using an absolute path.
     * Example: "/org/example/sound2textapp/styles/styles.css"
     */
    override fun loadURL(path: String): URL? {
        return ResourceLoader::class.java.getResource(path)
    }

    /*
     * Loads a resource relative to a given class.
     * Useful if your resources are in the same package as your class.
     */
    override fun loadRelative(clazz: Class<*>, relativePath: String): URL? {
        return clazz.getResource(relativePath)
    }

    /*
     * Loads a resource as a stream (useful for audio, images, etc.)
     */

    override fun loadAsStream(path: String): InputStream? {
        //var stream = getClass().getResourceAsStream("path/to/resource.file")
        var stream = javaClass.classLoader.getResourceAsStream(path)
        if (stream == null) {
            logger.error("Resource not found: $path")
            stream = load(path) // Now reassignment is allowed
        }
        return stream
    }
    override fun readTextFromResource(path: String): String? {
        return try {
            object {}.javaClass.classLoader
                .getResourceAsStream(path)
                ?.bufferedReader()
                ?.use { it.readText() }
        } catch (e: IOException) {
            logger.error("Error loading resource in utility function $e")
            return null
        }
    }

    private fun load(resourceName: String): InputStream? {
        return try {
            val callerClassName = Throwable().stackTrace[1].className
            val callerClass = Class.forName(callerClassName)
            val packageName = callerClass.`package`.name // e.g. "com.sildeag.sound2text.ui"

            val segments = packageName.split('.', '/')
            val baseSegments = segments.take(3)
            val basePath = baseSegments.joinToString("/")

            val sanitizedResourceName = resourceName.trim().trimStart('/')
            val fullPath = "$basePath/$sanitizedResourceName"

            val stream = Thread.currentThread().
                contextClassLoader.getResourceAsStream(fullPath)
            if (stream == null) {
                logger.warning("Resource not found: $fullPath")
            }
            return stream
        } catch (e: Exception) {
            logger.severe("Failed to load resource '$resourceName': ${e.message}")
            return null
        }
    }
}
