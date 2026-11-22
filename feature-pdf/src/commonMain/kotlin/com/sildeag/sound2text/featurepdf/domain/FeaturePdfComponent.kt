package com.sildeag.sound2text.featurepdf

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.sildeag.sound2text.di.FileProcessorFeature
import com.sildeag.sound2text.featurepdf.viewmodel.FormDiscoveryViewModel
import com.sildeag.sound2text.featurepdf.viewmodel.PdfFeatureViewModel

class FeaturePdfProcessorFeature(
    private val discoveryViewModel: FormDiscoveryViewModel,
    private val pdfViewModel: PdfFeatureViewModel
) : FileProcessorFeature {
    override val id: String = "feature3_wac_processor"
    override val title: String = "WAC File Processor"
    override val content: @Composable () -> Unit = {
        val forms by discoveryViewModel.forms.collectAsState()
        val pdfState by pdfViewModel.state.collectAsState()
        LaunchedEffect(Unit) {
            discoveryViewModel.loadForms()
        }
        FeaturePdfScreen(
            formsMap = forms,
            pdfState = pdfState,
            onPdfSelected = { pdfBytes ->
                pdfViewModel.dispatch(PdfFeatureActions.LoadUiPdf(pdfBytes))
                pdfViewModel.dispatch(PdfFeatureActions.ExtractText(pdfBytes))
            },
            onPageSelected = { index ->
                pdfViewModel.dispatch(PdfFeatureActions.RenderPage(index))
            }
        )
    }
}