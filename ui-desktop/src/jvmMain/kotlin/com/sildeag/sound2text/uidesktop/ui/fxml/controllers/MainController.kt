package com.sildeag.sound2text.uidesktop.ui.fxml.controllers

import com.sildeag.sound2text.uicommon.viewmodel.SoundViewModel
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
class MainController(
    private val viewModel: SoundViewModel
) {
    @FXML lateinit var inputPath: TextField
    @FXML lateinit var outputText: TextArea
    @FXML lateinit var transcribeButton: Button
    @FXML lateinit var saveButton: Button
    @FXML
    fun initialize() {
        transcribeButton.setOnAction {
            val path = inputPath.text
            viewModel.transcribe(path)
            outputText.text = viewModel.text.value
        }
        saveButton.setOnAction {
            viewModel.save("output")
        }
    }
}

//import com.sildeag.sound2text.ui.SoundUI
/*
import com.sildeag.sound2text.viewmodel.CoreSoundViewModel
import com.sildeag.sound2text.service.logic.PulseLogic
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.shape.Circle
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
class MainController : KoinComponent {


    private val pulseLogic by
    inject<PulseLogic>()
    private val core by inject<CoreSoundViewModel>
    { parametersOf(this) }
    @FXML lateinit var notesArea: TextArea
    @FXML lateinit var micButton: Button
    @FXML lateinit var pulseCircle: Circle
    @FXML
    fun initialize() {
        /*core.bindPulseLogic()
        micButton.setOnAction {
            core.toggleMic()
        }*/
    }
    fun updateText(text: String) {
        Platform.runLater { notesArea.text = text }
    }
    fun updateMicState(isOn: Boolean) {
        Platform.runLater {
            micButton.text = if (isOn) "Stop Mic" else "Start Mic"
        }
    }
    fun updatePulseColor(color: String) {
        Platform.runLater {
            pulseCircle.style = "-fx-fill: $color"
        }
    }
    @FXML
    fun onGrammarCheck() {
        core.correctGrammar()
    }
    @FXML
    fun onGeneratePdf() {
        core.generatePDF("output.pdf")
    }
}
*/
