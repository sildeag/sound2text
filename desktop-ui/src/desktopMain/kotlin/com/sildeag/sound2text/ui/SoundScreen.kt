package com.sildeag.sound2text.ui

import com.sildeag.sound2text.core.config.AppEnvironment
import com.sildeag.sound2text.di.SoundViewModelParams
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.scene.shape.Circle
import javafx.stage.FileChooser
import java.util.prefs.Preferences
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import javafx.event.ActionEvent

private val prefs = Preferences.userNodeForPackage(SoundScreen::class.java)

class SoundScreen : KoinComponent {

    private val viewModel: SoundViewModel by inject {
            parametersOf(
                SoundViewModelParams(
                    onText = { text: String ->
                        Platform.runLater {
                            notesArea.text = text
                            //viewModel.showToast("Recognized: $text")
                        }
                    },
                    onMic = { isOn: Boolean ->
                        Platform.runLater {
                            micButton.text = if (isOn) "Stop Mic"
                                else "Start Mic"
                            micButton.style = if (isOn)
                                "-fx-background-color: red"
                                else "-fx-background-color: green"
                        }
                    },
                    onPulse = { pulse: String -> println("Pulse: $pulse") },
                    onPulseColor = { color: String ->
                        Platform.runLater {
                            pulseCircle.style = "-fx-fill: $color"
                        }
                    },
                    onPulseUpdate = { update: String ->
                        println("Pulse update : $update") },

                    environment = AppEnvironment.current.name))
    }



                    @FXML private lateinit var notesArea: TextArea
                    @FXML private lateinit var nameField: TextField
                    @FXML private lateinit var emailField: TextField
                    @FXML private lateinit var micButton: Button
                    @FXML private lateinit var toastContainer: VBox
                    @FXML private lateinit var pulseCircle: Circle
                    @FXML private lateinit var sensitivityLabel: Label
                    @FXML private lateinit var sensitivitySlider: Slider
                    @FXML private lateinit var soundToggle: CheckBox

                    fun initialize() {
                        println("class controller is called")
                        notesArea.isEditable = true
                        notesArea.promptText = "Recognized text will appear here..."

                        micButton.setOnAction {
                            viewModel.toggleMic()
                        }
                        /*
                        viewModel.text = { text ->
                            Platform.runLater {
                                notesArea.text = text
                                showToast("Recognized: $text")
                            }
                        }


                        viewModel.onMic = { isOn ->
                            micButton.text = if (isOn) "Stop Mic" else "Start Mic"
                            micButton.style = if (isOn) "-fx-background-color: red" else "-fx-background-color: green"
                        }
                        viewModel.onPulseColor = { color ->
                            pulseCircle.style = "-fx-fill: $color"
                        }

                         */
                        notesArea.focusedProperty().addListener { _, _, hasFocus ->
                            if (!hasFocus) viewModel.onFocusLost()
                        }
                    }

                    @FXML
                    fun onResetSensitivity(event: ActionEvent) {
                        val defaultValue = 1.0
                        sensitivitySlider.value = defaultValue
                        sensitivityLabel.text = String.format("%.1fx", defaultValue)
                        prefs.putDouble("micSensitivity", defaultValue)
                    }

                    @FXML
                    fun onRestoreDefaults(event: ActionEvent) {
                        // 🔄 Reset sensitivity
                        val defaultSensitivity = 1.0
                        sensitivitySlider.value = defaultSensitivity
                        sensitivityLabel.text = String.format("%.1fx", defaultSensitivity)
                        prefs.putDouble("micSensitivity", defaultSensitivity)

                        // 🔔 Reset sound toggle
                        soundToggle.isSelected = true
                        prefs.putBoolean("soundEnabled", true)
                    }

                    @FXML
                    fun onGeneratePdf(event: ActionEvent) {
                        val chooser = FileChooser()
                        chooser.title = "Save PDF"
                        chooser.initialFileName = "speech_output.pdf"
                        chooser.extensionFilters.add(FileChooser.ExtensionFilter("PDF Files", "*.pdf"))

                        val name = nameField.text
                        val email = emailField.text
                        val notes = notesArea.text

                        val file = chooser.showSaveDialog(null)
                        if (file != null) {
                            viewModel.generatePDF(file.absolutePath)
                        }
                    }

                    @FXML
                    fun onSimulatePdf(event: ActionEvent) {
                        // your logic here
                    }


                    @FXML
                    fun onToggleMic(event: ActionEvent) {
                        viewModel.toggleMic()
                    }

                    @FXML
                    fun onGrammarCheck(event: ActionEvent) {
                        notesArea.text = viewModel.correctGrammar()
                        viewModel.showToast("Grammar corrected")
                    }
}

