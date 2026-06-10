package com.sildeag.sound2text.uidesktop.pdf

import androidx.compose.runtime.Composable
import com.itextpdf.kernel.pdf.PdfPage

@Composable
actual fun PdfPageView(page: PdfPage) {
    Image(bitmap = page.bitmap, contentDescription = null)
}