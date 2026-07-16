package com.sildeag.sound2text.featureform

sealed interface FormFeatureActions {
    data class LoadPdf(val bytes: ByteArray) : FormFeatureActions
    data class RenderPage(val pageIndex: Int) : FormFeatureActions
    data class ExtractFields(val bytes: ByteArray) : FormFeatureActions
    data class TranscribeAudio(val bytes: ByteArray, val fieldId: String) :
        FormFeatureActions
}
