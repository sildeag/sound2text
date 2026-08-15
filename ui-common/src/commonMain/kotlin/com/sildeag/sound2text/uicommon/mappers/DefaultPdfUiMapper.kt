package com.sildeag.sound2text.uicommon.mappers

import androidx.compose.ui.graphics.ImageBitmap
import com.sildeag.sound2text.core.pdf.model.PdfPage
import com.sildeag.sound2text.uicommon.models.UiPdfPage
/**
 * Default mapper converting core PdfPage into UiPdfPage.
 * Bitmap is supplied by platform renderers (Android/Desktop).
 */
class DefaultPdfUiMapper {
    fun map(
        core: PdfPage,
        bitmap: Any, // platform-specific ImageBitmap or BufferedImage
        width: Int,
        height: Int
    ): UiPdfPage =
        UiPdfPage(
            index = core.index,
            text = core.text,
            bitmap = bitmap as ImageBitmap,
            width = width,
            height = height
        )
}