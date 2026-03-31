package com.sildeag.sound2text.uidesktop

import androidx.compose.runtime.mutableStateOf
import com.sildeag.sound2text.core.config.AppSettings
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.sttdesktop.SttEngine
import com.sildeag.sound2text.sttdesktop.SttResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch

class DesktopSttController(
    private val settings: AppSettings,
    private val engine: SttEngine,
    private val logger: Logger
) {
    val textOutput = mutableStateOf("")
    val isRunning = mutableStateOf(false)
    private var job: Job? = null
    fun start() {
        if (isRunning.value) return
        isRunning.value = true
        val audio = BufferedJvmMicrophoneSource(
            sampleRate = settings.audio.sampleRate,
            channels = 1
        )
        val streaming = engine.streaming(settings.stt, audio)
        job = CoroutineScope(Dispatchers.IO).launch {
            streaming.collect { result ->
                when (result) {
                    is SttResult.Success -> textOutput.value = result.text
                    is SttResult.Failure -> textOutput.value = "Error: $
                    {result.error}"
                }
            }
        }
    }
    suspend fun stop() {
        isRunning.value = false
        job?.cancelAndJoin()
    }
}
