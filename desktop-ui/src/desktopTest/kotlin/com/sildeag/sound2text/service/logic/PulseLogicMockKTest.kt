package com.sildeag.sound2text.service.logic

import com.sildeag.sound2text.service.recognizer.Recognizer
import com.sildeag.sound2text.service.note.NoteEditor
import com.sildeag.sound2text.audio.SoundPlayer
import com.sildeag.sound2text.di.testModule
import com.sildeag.sound2text.core.logging.Logger
import io.mockk.mockk
import io.mockk.verify
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.slot
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.junit5.KoinTestExtension
import javafx.application.Platform
import org.koin.core.qualifier.named

class PulseLogicTest : KoinTest {

    @JvmField
    @RegisterExtension
    val koinTestExtension = KoinTestExtension.create { modules(testModule) }

    private val pulseLogic: PulseLogic by inject()

    // Inject the mocked dependencies for verification
    private val recognizer: Recognizer by inject()
    private val soundPlayer: SoundPlayer by inject()
    private val noteEditor: NoteEditor by inject()
    private val logger: Logger by inject()

    // Inject the mocked callback functions
    private val onText: (String) -> Unit by inject(qualifier = named("onText"))
    private val onMic: (Boolean) -> Unit by inject(qualifier = named("onMic"))

    @BeforeEach
    fun setup() {
        // Mock the static JavaFX Platform.runLater
        mockkStatic(Platform::class)
        val runnableSlot = slot<Runnable>()
        every { Platform.runLater(capture(runnableSlot)) } answers { runnableSlot.captured.run() }
    }

    @Test
    fun `startListening starts sound and recognizer, and logs`() {
        // When
        pulseLogic.startListening()

        // Then
        verify { soundPlayer.playSmartSound("/sounds/mic_click_on.wav") }
        verify { recognizer.start(any()) }
        verify { logger.log("Recording started 🟢🎤") }
    }

    @Test
    fun `stopListening stops sound and recognizer, and resets mic state`() {
        // When
        pulseLogic.stopListening()

        // Then
        verify { soundPlayer.playSmartSound("/sounds/mic_click_off.wav") }
        verify { recognizer.stop() }
        verify { onMic(false) }
    }

    @Test
    fun `processText appends text to note editor and invokes onText callback`() {
        // Given
        val testText = "Hello, world!"
        val existingNote = "Existing note."
        every { noteEditor.getText() } returns "$existingNote$testText"

        // When
        pulseLogic.processText(testText)

        // Then
        verify { noteEditor.append(testText) }
        verify { onText("$existingNote$testText") }
        verify { logger.log("Processed: $existingNote$testText") }
    }

    @Test
    fun `config updates callback functions`() {
        // Given
        val newOnText: (String) -> Unit = mockk()
        val newOnMic: (Boolean) -> Unit = mockk()
        val newOnPulse: (String) -> Unit = mockk()

        // When
        pulseLogic.pulseConfig(newOnText, newOnMic, newOnPulse)
        pulseLogic.processText("test") // Trigger an action that uses the callbacks

        // Then
        verify { noteEditor.append("test") }
        verify { newOnText.invoke("test") }
    }
}