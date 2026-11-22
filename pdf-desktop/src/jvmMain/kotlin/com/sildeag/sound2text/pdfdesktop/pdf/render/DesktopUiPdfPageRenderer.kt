package com.sildeag.sound2text.pdfdesktop.pdf.render

import androidx.compose.ui.graphics.ImageBitmap
import com.sildeag.sound2text.uicommon.pdf.render.UiPdfPageRenderer
import com.sildeag.sound2text.pdfdesktop.pdf.render.pdfbox.PdfBoxRenderer
import kotlin.coroutines.cancellation.CancellationException

class DesktopUiPdfPageRenderer : UiPdfPageRenderer {
    override suspend fun renderBitmapForPage(bytes: ByteArray, index: Int): ImageBitmap? {
        return try {
            val renderer = PdfBoxRenderer(bytes)
            val rendered = renderer.render(index)
            rendered.bitmap
        } catch (e: CancellationException) {
            throw e
        } catch (_: Throwable) {
            null
        }
    }
}
