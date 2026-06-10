package com.sildeag.sound2text.uiandroid.pdf

import androidx.compose.runtime.Composable

@Composable
actual fun PdfPageView(page: PdfPage) {
    AndroidPdfPage(page)
}