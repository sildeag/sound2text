package com.sildeag.sound2text.uicommon.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sildeag.sound2text.uicommon.pdf.PdfViewModel

@Composable
fun PdfScreen(
    path: String,
    page: Int,
    pdfViewModel: PdfViewModel
) {
    Text("PDF Screen: $path (page $page)")
}
