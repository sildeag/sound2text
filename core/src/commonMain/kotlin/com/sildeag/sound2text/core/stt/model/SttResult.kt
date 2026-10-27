package com.sildeag.sound2text.core.stt.model

sealed class SttResult {
    data class Final(val text: String) : SttResult()
    data class Partial(val text: String) : SttResult()
    data class Error(val message: String) : SttResult()
}
/**
 * Lightweight data transfer object bridging raw backend data
 * to final/partial states inside the orchestrator.
 */
data class UnifiedBackendResult(val text: String, val isFinal: Boolean)