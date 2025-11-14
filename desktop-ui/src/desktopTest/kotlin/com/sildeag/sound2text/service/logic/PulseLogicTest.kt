package com.sildeag.sound2text.service.logic

import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.di.PulseLogicParams
import com.sildeag.sound2text.di.TestModules
import com.sildeag.sound2text.di.loadEnvironmentModules
import com.sildeag.sound2text.test.KoinTestHarness
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.Test
import kotlin.test.assertEquals

class PulseLogicTest : KoinTestHarness(
    environment = Environment.TEST,
    moduleOverrides = {
        includes(TestModules.pulseLogicTest)
        includes(TestModules.recognizerMock)
        includes(TestModules.noteEditorMock)
        includes(TestModules.callbackMocks)
    }
)

class PulseLogicTest : KoinTestHarness({
    loadEnvironmentModules()
}), KoinTest {
    @Test
    fun `processText triggers recognized callback`() {
        // Arrange
        var recognizedText = ""
        var micState = false
        var pulseColor = ""
        val onText: (String) -> Unit = { recognizedText = it }
        val onMicState: (Boolean) -> Unit = { micState = it }
        val onPulseColor: (String) -> Unit = { pulseColor = it }
        val pulseParams = PulseLogicParams(
            onText = onText,
            onMic = onMicState,
            onPulse = { /* optional */ },
            onPulseUpdate = { /* optional */ },
            onPulseColor = onPulseColor,
            recognizerFactory = { get() },
            environment = "DEV"
        )
        val pulseLogic: PulseLogic = get {
            parametersOf(pulseParams)
        }
        // Act
        pulseLogic.triggerText("Hello world")
        // Assert
        assertEquals("Hello world", recognizedText)
    }

}