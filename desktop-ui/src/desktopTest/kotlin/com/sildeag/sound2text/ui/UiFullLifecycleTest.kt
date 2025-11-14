package com.sildeag.sound2text.ui

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.get
import com.sildeag.sound2text.di.devModule
import com.sildeag.sound2text.service.note.NoteProvider
import com.sildeag.sound2text.audio.SoundPlayer
import com.sildeag.sound2text.service.logic.PulseLogic
import com.sildeag.sound2text.ui.MainController
import kotlin.test.assertNotNull
class UILifecycleTest : KoinTest {
    @Test
    fun `should wire controller and services correctly`() {
        startKoin {
            modules(devModule)
        }
        val loader =
            FXMLLoader(javaClass.getResource("/com/sildeag/sound2text/view/main_view.fxml"))
        val root: Parent = loader.load()
        val controller: MainController = loader.getController()
        val soundPlayer: SoundPlayer = get()
        val noteProvider: NoteProvider = get()
        val pulseLogic: PulseLogic = get {
            parametersOf(
                controller::handleRecognizedText,
                controller::updateMicState,
                controller::updatePulseColor
            )
        }
        controller.soundPlayer = soundPlayer
        controller.noteProvider = noteProvider
        controller.pulseLogic = pulseLogic
        // Assertions
        assertNotNull(controller.soundPlayer)
        assertNotNull(controller.noteProvider)
        assertNotNull(controller.pulseLogic)
    }
}
