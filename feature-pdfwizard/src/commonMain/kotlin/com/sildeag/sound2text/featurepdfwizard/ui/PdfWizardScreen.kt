package com.sildeag.sound2text.featurepdfwizard.ui

import androidx.compose.runtime.Composable
import com.sildeag.sound2text.featurepdfwizard.controller.PdfWizardController
import com.sildeag.sound2text.uicommon.state.PdfWizardWorkflowState

@Composable
fun PdfWizardScreen(
    state: PdfWizardWorkflowState,
    controller: PdfWizardController
) {
    when (state.currentStep) {
        PdfWizardWorkflowStep.SelectFile ->
            PdfWizardSelectFileScreen(controller)
        PdfWizardWorkflowStep.DiscoverFields ->
            PdfWizardDiscoverFieldsScreen(state, controller)
        PdfWizardWorkflowStep.FillFields ->
            PdfWizardFillFieldsScreen(state, controller)
        PdfWizardWorkflowStep.Review ->
            PdfWizardReviewScreen(state, controller)
        PdfWizardWorkflowStep.Save ->
            PdfWizardSaveScreen(state, controller)

        PdfWizardWorkflowStep.LoadDocument -> TODO()
    }
}