package com.sildeag.sound2text.featurepdf

sealed interface PdfFeatureActions {
    data class LoadPdf(val bytes: ByteArray) : PdfFeatureActions
    data class RenderPage(val pageIndex: Int) : PdfFeatureActions
    data class ExtractText(val bytes: ByteArray) : PdfFeatureActions
}