package com.sildeag.sound2text.featurestt

import com.sildeag.sound2text.core.dispatchers.DispatcherProvider
import com.sildeag.sound2text.core.logging.Logger
import com.sildeag.sound2text.core.resource.ResourceLoader
import com.sildeag.sound2text.core.stt.engine.SttEngine
import com.sildeag.sound2text.core.stt.model.SttResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SttFeatureViewModel(
    private val sttEngine: SttEngine,
    private val dispatchers: DispatcherProvider,
    private val logger: Logger,
    private val resources: ResourceLoader
) {
    private val scope = CoroutineScope(SupervisorJob() + dispatchers.default)
    private val _state = MutableStateFlow(SttFeatureState())
    val state: StateFlow<SttFeatureState> = _state
    fun dispatch(action: SttFeatureActions) {
        when (action) {
            is SttFeatureActions.TranscribeAudio -> transcribe(action.bytes)
        }
    }
    private fun transcribe(bytes: ByteArray) {
        scope.launch {
            _state.value = _state.value.copy(isProcessing = true)

            try {
                val result = withContext(dispatchers.io) {
                    sttEngine.processAudio(bytes)
                }

                when (result) {
                    is SttResult.Partial -> {
                        _state.value = _state.value.copy(
                            transcript = result.text,
                            isProcessing = false
                        )
                    }

                    is SttResult.Final -> {
                        _state.value = _state.value.copy(
                            transcript = result.text,
                            isProcessing = false
                        )
                    }

                    is SttResult.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message,
                            isProcessing = false
                        )
                    }
                }

                logger.d("SttFeature", "Transcription complete")

            } catch (t: Throwable) {
                logger.e("SttFeature", "Transcription failed", t)
                _state.value = _state.value.copy(
                    error = t.message,
                    isProcessing = false
                )
            }
        }
    }
}