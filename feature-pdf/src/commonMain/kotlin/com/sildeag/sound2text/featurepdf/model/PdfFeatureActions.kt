package com.sildeag.sound2text.featurepdf

sealed interface PdfFeatureActions {
    data class LoadUiPdf(val bytes: ByteArray) : PdfFeatureActions
    data class RenderPage(val pageIndex: Int) : PdfFeatureActions
    data class ExtractText(val bytes: ByteArray) : PdfFeatureActions
}