package com.sildeag.sound2text.ui

import com.sildeag.sound2text.core.config.AppEnvironment
import com.sildeag.sound2text.`desktop-ui`.ui.SoundViewModel
import com.sildeag.sound2text.di.SoundViewModelParams
import com.sildeag.sound2text.di.loadEnvironmentModules
import com.sildeag.sound2text.test.KoinTestHarness
import javafx.application.Platform
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.control.Slider
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import javafx.scene.shape.Circle
import javafx.stage.FileChooser
import org.junit.jupiter.api.BeforeEach
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.testfx.api.FxAssert
import org.testfx.matcher.control.LabeledMatchers
import org.testfx.matcher.control.TextInputControlMatchers
import kotlin.test.Test
import kotlin.getValue

class SoundScreenTest : KoinTestHarness({
    loadEnvironmentModules()
}), KoinTest {

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
    @BeforeEach
    fun setupUI() {
        micButton = lookup("#micButton").query()
        notesArea = lookup("#notesArea").query()
    }

    private fun clickOn(micButton: Button) {}

    @Test
    fun `mic button should toggle text`() {
        clickOn(micButton)
        FxAssert.verifyThat(micButton, LabeledMatchers.hasText("Stop Mic"))
        clickOn(micButton)
        FxAssert.verifyThat(micButton,
            LabeledMatchers.hasText("Start Mic"))
    }
    @Test
    fun `notes area should update with recognized text`() {
        interact {
            notesArea.appendText("hello world ")
        }
        FxAssert.verifyThat(notesArea,
            TextInputControlMatchers.hasText("hello world "))
    }
    @Test
    fun `notes area should show pulse style`() {
        interact {
            notesArea.style = "-fx-border-color: green;"
        }
        assert(notesArea.style.contains("green"))
    }
}

