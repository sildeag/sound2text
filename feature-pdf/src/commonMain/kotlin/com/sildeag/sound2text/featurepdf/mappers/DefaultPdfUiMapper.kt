package com.sildeag.sound2text.featurepdf.mappers

import androidx.compose.ui.graphics.ImageBitmap
import com.sildeag.sound2text.core.pdf.model.PdfPage
import com.sildeag.sound2text.featurepdf.models.UiPdfPage

class DefaultPdfUiMapper : PdfUiMapper<UiPdfPage> {

    override fun map(
        core: PdfPage,
        bitmap: ImageBitmap,
        width: Int,
        height: Int
    ): UiPdfPage =
        UiPdfPage(
            index = core.index,
            text = core.text,
            bitmap = bitmap,
            width = width,
            height = height,
            pageNumber = core.index + 1
        )
}
