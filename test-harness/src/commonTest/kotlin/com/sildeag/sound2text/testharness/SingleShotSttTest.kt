package com.sildeag.sound2text.testharness

import com.sildeag.sound2text.core.stt.*

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
    fun testSingleShotTranscription(): Unit = runTest {
        val config = SttConfig(
            engineName = "vosk",
            language = "en-US",
            modelPath = "/models/vosk/en",
            modelFile = "model.bin",
            androidModelDir = null,
            androidModelFile = null,
            sampleRate = 16000f
        )
        val engine = harness.loadEngine(config)
        assertNotNull(engine)
        val audio =
            TestAudioLoader.loadWavResource("audio/test1.wav")
        val result = engine.processAudio(audio)
        assertTrue(result.text.isNotBlank(),
            "Transcription result should not be empty")
    }
}

