package com.sildeag.sound2text.core.stt

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class SttStreamingController(
    private val engine: SttEngine
) {
    private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    private val _partial = MutableSharedFlow<String>(replay = 1)
    val partial: SharedFlow<String> = _partial

    private val _final = MutableSharedFlow<String>(replay = 1)
    val final: SharedFlow<String> = _final

    private val _errors = MutableSharedFlow<String>(replay = 1)
    val errors: SharedFlow<String> = _errors


    suspend fun startStreaming (

    ) {
        engine.startStreaming(
            onPartial = { text ->
                scope.launch { _partial.emit(text) }
            },
            onFinal = { text ->
                scope.launch { _final.emit(text) }
            },
            onError = { msg ->
                scope.launch { _errors.emit(msg) }
            }
        )
    }

    fun processAudio(bytes: ByteArray) {
        engine.processAudio(bytes)
    }

    suspend fun stop() {
        engine.stop()
    }
}
