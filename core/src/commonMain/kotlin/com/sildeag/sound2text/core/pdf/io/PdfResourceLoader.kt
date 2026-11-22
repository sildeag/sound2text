
package com.sildeag.sound2text.core.pdf.io

interface PdfResourceLoader {
    /**
     * Loads raw file bytes from disk/assets/network.
     */
    suspend fun loadBytes(path: String): ByteArray

    /**
     * Legacy/Existing method returning a core PdfDocument wrapper.
     */
    suspend fun load(path: String): ByteArray
}