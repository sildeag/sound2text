package com.sildeag.sound2text.testharness

import com.sildeag.sound2text.core.stt.SttConfig
import com.sildeag.sound2text.core.stt.UnifiedEngineRegistry
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertNotNull

class SingleShotSttTest {
    private val registry = UnifiedEngineRegistry(
        plugins = listOf(
            // VoskDesktopPlugin(),
            // AndroidSpeechPlugin(),
            // CombinedSttPlugin()
        )
    )
    private val harness = UnifiedSttTestHarness(registry)
    @Test
    fun testSingleShotTranscription() = runTest {
        val config = SttConfig(
            language = "en-US",
            engineName = "vosk",
            modelPath = "/models/vosk/en",
            modelFile = "model.bin",
            androidModelDir = null,
            androidModelFile = null,
            sampleRate = 16000f
        )
        val audio = TestAudioLoader.loadWavResource("hello_world.wav")
        val result = harness.runSingleShot(config, audio)
        assertTrue(result.transcript.isNotBlank(),
            "Transcription result should not be empty")
    }

}

