package com.sildeag.sound2text.uicommon.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.asImageBitmap
import com.sildeag.sound2text.uicommon.models.UiPdfPage
@Composable
fun PdfPageView(
    page: UiPdfPage
) {
    val bmp = page.bitmap ?: return
    Image(
        bitmap = bmp,
        contentDescription = "PDF Page ${page.index}"
    )
}
