package com.sildeag.sound2text.uiandroid.pdf

@Composable
fun PdfWizardWorkflowScreen(
    controller: PdfWizardController,
    recorder: PlatformAudioRecorder, // Desktop or Android
    onClose: () -> Unit
) {
    var state by remember { mutableStateOf(controller.state) }
    val audioBuffer = remember { ByteArrayOutputStream() }
    LaunchedEffect(controller.state) {
        state = controller.state
    }
    when (val step = state.step) {
        PdfWizardStep.SelectForm -> {
            FormSelectionScreen(
                forms = state.availableForms,
                onSelect = {
                    controller.selectForm(it)
                    state = controller.state
                },
                onClose = onClose
            )
        }
        is PdfWizardStep.MapFields -> {
            FieldMappingScreen(
                form = step.form,
                onMappingsReady = {
                    controller.setMappings(it)
                    state = controller.state
                },
                onBack = {
                    controller.loadForms()
                    state = controller.state
                }
            )
        }
        PdfWizardStep.FillFields -> {
            val current = state.currentField
            if (current == null) {
                Text("No more fields.")
                return
            }
            Column {
                Text("Recording for: ${current.field.name}")
                Text("Engine: ${current.sttEngine}")
                Row {
                    Button(onClick = {
                        audioBuffer.reset()
                        recorder.start { chunk ->
                            audioBuffer.write(chunk) }
                    }) { Text("Start") }
                    Button(onClick = {
                        recorder.stop()
                        val audioBytes = audioBuffer.toByteArray()

                        controller.fillCurrentFieldWithStt(audioBytes, "/tmp/output.pdf")
                        state = controller.state
                    }) { Text("Stop & Fill") }
                }
            }
        }
        PdfWizardStep.Completed -> {
            CompletedScreen(
                outputPath = state.outputPath,
                onClose = onClose
            )
        }
    }
}
