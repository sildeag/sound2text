package com.sildeag.sound2text.ui

import com.sildeag.sound2text.core.config.Environment
import com.sildeag.sound2text.`desktop-ui`.ui.SoundViewModel
import com.sildeag.sound2text.di.SoundViewModelParams
import com.sildeag.sound2text.di.mockRecognizerModule
import com.sildeag.sound2text.di.testPulseModule
import com.sildeag.sound2text.test.KoinTestHarness
import org.koin.core.parameter.parametersOf
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MicToggleSenarioTest : KoinTestHarness(
    environment = Environment.TEST,
    moduleOverrides = {
        includes(mockRecognizerModule, testPulseModule)
    }
) {
    private val viewModel: SoundViewModel by inject {
        parametersOf(
            SoundViewModelParams(
                onText = { /* test callback */ },
                onMic = { /* test callback */ },
                onPulse = { /* test callback */ },
                onPulseColor = { /* test callback */ },
                onPulseUpdate = { /* test callback */ },
                environment = "TEST"
            )
        )
    }

    class PulseTestObserver {
        val textEvents = mutableListOf<String>()
        val micEvents = mutableListOf<Boolean>()
        val pulseColors = mutableListOf<String>()
        val onText: (String) -> Unit = { textEvents += it }
        val onMic: (Boolean) -> Unit = { micEvents += it }
        val onPulse: (String) -> Unit = { pulseColors += it }
    }

    val observer = PulseTestObserver()


    @Test
    fun `should toggle mic and update UI`() {
        viewModel.toggleMic()
        viewModel.isListening
        val config = viewModel.pulseLogic.lastConfig
        val observer = PulseTestObserver()
        viewModel.pulseLogic.pulseConfig(
            onText = observer.onText,
            onMic = observer.onMic,
            onPulse = observer.onPulse
        )

        config?.onMic?.invoke(true)
        config?.onText?.invoke("Hello world")
        config?.onText?.invoke("red")
        assertTrue(observer.micEvents.contains(true))
        assertEquals("Hello world", observer.textEvents.last())
        assertEquals("red", observer.pulseColors.first())

    /*
        Notes
                viewModel.pulseLogic.config
                onMic = { isOn ->
                isListening = isOn
                onMic(isOn)
            }
     */
}